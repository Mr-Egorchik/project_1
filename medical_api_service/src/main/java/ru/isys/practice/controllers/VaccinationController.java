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

    @Autowired
    public VaccinationController(VaccinationService vaccinationService) {
        this.vaccinationService = vaccinationService;
    }

    @PostMapping(value = "/process-file", consumes = "multipart/form-data")
    public ResponseEntity<Vaccination> processFile(@RequestBody MultipartFile file) throws IOException {
        vaccinationService.saveFromFile(file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/vaccination")
    public List<VaccinationDto> getByPassport (@RequestParam("passport") String passport) {
        return vaccinationService.getByPassport(passport);
    }

}