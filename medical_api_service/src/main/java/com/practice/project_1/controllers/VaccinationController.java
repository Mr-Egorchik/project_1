package com.practice.project_1.controllers;

import au.com.bytecode.opencsv.CSVReader;
import com.practice.project_1.dto.PersonDto;
import com.practice.project_1.dto.VaccinationDto;
import com.practice.project_1.entity.Vaccination;
import com.practice.project_1.services.VaccinationService;
import com.practice.project_1.services.mapping.VaccinationMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/")
public class VaccinationController {

    @Autowired
    private VaccinationService vaccinationService;

    @Autowired
    private VaccinationMapping vaccinationMapping;

    @PostMapping(value = "/process-file", consumes = "multipart/form-data")
    public ResponseEntity<Vaccination> processFile(@RequestBody MultipartFile file) throws IOException {
        CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(file.getInputStream())), ';');
        String[] current;
        while ((current=reader.readNext())!=null) {
            RestTemplate restTemplate = new RestTemplate();
            current[0] = current[0].substring(1);
            current[2] = current[2].replaceAll(" ", "");
            current[2] = new StringBuilder(current[2]).insert(4, " ").toString();
            String url = "http://localhost:9090/persons/verify?name=" + current[1] + "&passport=" +current[2];
            Map<String, String> map = new HashMap<>();
            map.put("name", current[1]);
            map.put("passport", current[2]);
            ResponseEntity<PersonDto> response = restTemplate.getForEntity(url, PersonDto.class);
            if (response.getStatusCode().equals(HttpStatus.OK))
                vaccinationService.save(new VaccinationDto(UUID.fromString(current[0]), LocalDate.parse(current[3]), current[1], current[2], current[4], current[5]));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/vaccination")
    public List<VaccinationDto> getByPassport (@RequestParam("passport") String passport) {
        List<Vaccination> vaccinations = vaccinationService.getByPassport(passport);
        if (vaccinations == null)
            return null;
        List<VaccinationDto> res = new ArrayList<>();
        for (Vaccination vaccination: vaccinations)
            res.add(vaccinationMapping.entityToDto(vaccination));
        return res;
    }

}