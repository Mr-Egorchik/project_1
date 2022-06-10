package ru.isys.practice.services;

import ru.isys.practice.entity.VaccinationCenter;
import ru.isys.practice.repositories.VaccinationCenterRepository;
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
        return vaccinationCenterRepository.findByName(name);
    }
}
