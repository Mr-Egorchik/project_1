package com.practice.project_1.services;

import com.practice.project_1.entity.VaccinationCenter;
import com.practice.project_1.repositories.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VaccinationCenterService {

    private final VaccinationCenterRepository vaccinationCenterRepository;

    @Autowired
    public VaccinationCenterService(VaccinationCenterRepository vaccinationCenterRepository) {
        this.vaccinationCenterRepository = vaccinationCenterRepository;
    }

    public void save(VaccinationCenter vaccinationCenter) {
        vaccinationCenterRepository.save(vaccinationCenter);
    }

    public VaccinationCenter findById(UUID uuid) {
        return vaccinationCenterRepository.findById(uuid).orElse(null);
    }

    public VaccinationCenter findByName(String name) {
        UUID uuid = vaccinationCenterRepository.findByName(name);
        if (uuid == null) {
            return null;
        }
        return findById(uuid);
    }
}
