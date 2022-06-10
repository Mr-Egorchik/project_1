package ru.isys.practice.services;

import ru.isys.practice.dto.VaccinationDto;
import ru.isys.practice.entity.Vaccination;
import ru.isys.practice.entity.VaccinationCenter;
import ru.isys.practice.entity.Vaccine;
import ru.isys.practice.repositories.VaccinationRepository;
import ru.isys.practice.services.mapping.VaccinationMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VaccinationService {

    private final VaccinationRepository vaccinationRepository;
    private final VaccinationMapping vaccinationMapping;
    private final VaccinationCenterService vaccinationCenterService;
    private final VaccineService vaccineService;

    @Autowired
    public VaccinationService(VaccinationRepository vaccinationRepository, VaccinationMapping vaccinationMapping, VaccinationCenterService vaccinationCenterService, VaccineService vaccineService) {
        this.vaccinationRepository = vaccinationRepository;
        this.vaccinationMapping = vaccinationMapping;
        this.vaccinationCenterService = vaccinationCenterService;
        this.vaccineService = vaccineService;
    }

    public void save(VaccinationDto vaccinationDto) {
        VaccinationCenter vaccinationCenter = vaccinationCenterService.findByName(vaccinationDto.getVaccinationCenterName());
        Vaccine vaccine = vaccineService.findByName(vaccinationDto.getVaccineName());
        if (vaccinationCenter!=null && vaccine!=null) {
            Vaccination vaccination = vaccinationMapping.dtoToEntity(vaccinationDto, vaccine, vaccinationCenter);
            vaccinationRepository.save(vaccination);
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
