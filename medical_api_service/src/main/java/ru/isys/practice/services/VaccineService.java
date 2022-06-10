package ru.isys.practice.services;

import ru.isys.practice.entity.Vaccine;
import ru.isys.practice.repositories.VaccineRepository;
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
        return vaccineRepository.findByName(name);
    }
}
