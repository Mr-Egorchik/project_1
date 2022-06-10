package com.practice.project_1.services;

import com.practice.project_1.entity.Vaccine;
import com.practice.project_1.repositories.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VaccineService {

    private final VaccineRepository vaccineRepository;

    @Autowired
    public VaccineService(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }

    public void save(Vaccine vaccine) {
        vaccineRepository.save(vaccine);
    }

    public Vaccine findById(UUID uuid) {
        return vaccineRepository.findById(uuid).orElse(null);
    }

    public Vaccine findByName(String name) {
        UUID uuid = vaccineRepository.findByName(name);
        if (uuid == null) {
            return null;
        }
        return findById(uuid);
    }
}
