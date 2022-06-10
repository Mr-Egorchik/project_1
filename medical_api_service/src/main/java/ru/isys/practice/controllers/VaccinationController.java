package ru.isys.practice.controllers;

import au.com.bytecode.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import ru.isys.practice.dto.PersonDto;
import ru.isys.practice.dto.VaccinationDto;
import ru.isys.practice.entity.Vaccination;
import ru.isys.practice.services.VaccinationService;
import ru.isys.practice.services.mapping.VaccinationMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/")
public class VaccinationController {

    private final VaccinationService vaccinationService;
    private final VaccinationMapping vaccinationMapping;

    private static final String url = "http://localhost:9090/persons/verify?name=";

    @Autowired
    public VaccinationController(VaccinationService vaccinationService, VaccinationMapping vaccinationMapping) {
        this.vaccinationService = vaccinationService;
        this.vaccinationMapping = vaccinationMapping;
    }

    @PostMapping(value = "/process-file", consumes = "multipart/form-data")
    public ResponseEntity<Vaccination> processFile(@RequestBody MultipartFile file) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CSVReader reader = new CSVReader(bufferedReader, ';');
            String[] current;
            while ((current = reader.readNext()) != null) {
                RestTemplate restTemplate = new RestTemplate();
                current[0] = current[0].substring(1);
                current[2] = current[2].replaceAll(" ", "");
                current[2] = new StringBuilder(current[2]).insert(4, " ").toString();
                StringBuilder requestUrl = new StringBuilder(url);
                requestUrl.append(current[1]).append("&passport=").append(current[2]);
                ResponseEntity<?> response = restTemplate.getForEntity(requestUrl.toString(), PersonDto.class);
                if (response.getStatusCode().equals(HttpStatus.OK))
                    vaccinationService.save(new VaccinationDto(UUID.fromString(current[0]), LocalDate.parse(current[3]), current[1], current[2], current[4], current[5]));
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/vaccination")
    public List<VaccinationDto> getByPassport (@RequestParam("passport") String passport) {
        return vaccinationService.getByPassport(passport);
    }

}