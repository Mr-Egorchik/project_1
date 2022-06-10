package com.practice.project_1.services;

import com.practice.project_1.dto.VaccinationDto;
import com.practice.project_1.entity.Vaccination;
import com.practice.project_1.repositories.VaccinationRepository;
import com.practice.project_1.services.mapping.VaccinationMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VaccinationService {

    private final VaccinationRepository vaccinationRepository;
    private final VaccinationMapping vaccinationMapping;

    @Autowired
    public VaccinationService(VaccinationRepository vaccinationRepository, VaccinationMapping vaccinationMapping) {
        this.vaccinationRepository = vaccinationRepository;
        this.vaccinationMapping = vaccinationMapping;
    }

    public void save(VaccinationDto vaccinationDto) {
        Vaccination vaccination = vaccinationMapping.dtoToEntity(vaccinationDto);
        if (vaccination != null) {
            vaccinationRepository.save(vaccination);
        }
    }

    public List<Vaccination> getByPassport(String passport) {
        List<Vaccination> res = new ArrayList<>();
        List<UUID> uuids = vaccinationRepository.getByPassport(passport);
        if (uuids == null)
            return null;
        for (UUID uuid: uuids) {
            res.add(findById(uuid));
        }
        return res;
    }

    public Vaccination findById(UUID uuid) {
        return vaccinationRepository.findById(uuid).orElse(null);
    }
}
