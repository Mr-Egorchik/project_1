package ru.isys.practice.services;

import au.com.bytecode.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import ru.isys.practice.dto.PersonDto;
import ru.isys.practice.dto.StringFromFileDto;
import ru.isys.practice.dto.VaccinationDto;
import ru.isys.practice.entity.Vaccination;
import ru.isys.practice.entity.VaccinationCenter;
import ru.isys.practice.entity.Vaccine;
import ru.isys.practice.repositories.VaccinationRepository;
import ru.isys.practice.services.mapping.VaccinationMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class VaccinationService {

    private final VaccinationRepository vaccinationRepository;
    private final VaccinationMapping vaccinationMapping;
    private final VaccinationCenterService vaccinationCenterService;
    private final VaccineService vaccineService;
    private static final Logger log = LoggerFactory.getLogger(VaccinationService.class);
    private static final String url = "http://localhost:9090/persons/verify?name={name}&passport={passport}";
    @Autowired
    public VaccinationService(VaccinationRepository vaccinationRepository, VaccinationMapping vaccinationMapping, VaccinationCenterService vaccinationCenterService, VaccineService vaccineService) {
        this.vaccinationRepository = vaccinationRepository;
        this.vaccinationMapping = vaccinationMapping;
        this.vaccinationCenterService = vaccinationCenterService;
        this.vaccineService = vaccineService;
    }

    public void save(VaccinationDto vaccinationDto) {
        VaccinationCenter vaccinationCenter = vaccinationCenterService.findByName(vaccinationDto.getVaccinationCenterName());
        if (vaccinationCenter==null) {
            log.error("Vaccination center is not found!");
            return;
        }
        Vaccine vaccine = vaccineService.findByName(vaccinationDto.getVaccineName());
        if (vaccine==null) {
            log.error("Vaccine is not found!");
            return;
        }
        Vaccination vaccination = vaccinationMapping.dtoToEntity(vaccinationDto, vaccine, vaccinationCenter);
        vaccinationRepository.save(vaccination);
    }

    public void saveFromFile(MultipartFile file) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CSVReader reader = new CSVReader(bufferedReader, ';');
            String[] current;
            int i = 0;
            while ((current = reader.readNext()) != null) {
                ++i;
                if (current.length!=6) {
                    log.error("Wrong file");
                    break;
                }
                RestTemplate restTemplate = new RestTemplate();
                try {
                    StringFromFileDto string = new StringFromFileDto(current);
                    restTemplate.getForEntity(url, PersonDto.class, string.getName(), string.getPassport());
                    log.info("Passport-name is valid");
                    save(new VaccinationDto(UUID.fromString(string.getIdString()), LocalDate.parse(string.getDateString()), string.getName(), string.getPassport(), string.getVaccineName(), string.getVaccinationCenterName()));
                    log.info("Vaccination from string " + i + " is saved!");
                }
                catch (IndexOutOfBoundsException e) {
                    log.error("String " + i + " is wrong. Data will not be saved!");
                }
                catch (IllegalArgumentException e) {
                    log.error("Wrong field UUID");
                }
                catch (DateTimeParseException e) {
                    log.error("Wrong field Date");
                }
                catch (HttpClientErrorException e) {
                    log.error("Passport-name is not valid in string " + i);
                }
            }
        }
    }
    public List<VaccinationDto> getByPassport(String passport) {
        List<VaccinationDto> res = new ArrayList<>();
        List<Vaccination> vaccinations = vaccinationRepository.getByPassport(passport);
        for (Vaccination vaccination: vaccinations) {
            res.add(vaccinationMapping.entityToDto(vaccination));
        }
        return res;
    }

    public Vaccination findById(UUID uuid) {
        return vaccinationRepository.findById(uuid).orElse(null);
    }
}
