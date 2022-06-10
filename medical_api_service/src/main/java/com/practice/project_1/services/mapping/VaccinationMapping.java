package com.practice.project_1.services.mapping;

import com.practice.project_1.dto.VaccinationDto;
import com.practice.project_1.entity.Vaccination;
import com.practice.project_1.entity.VaccinationCenter;
import com.practice.project_1.entity.Vaccine;
import com.practice.project_1.services.VaccinationCenterService;
import com.practice.project_1.services.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationMapping {

    @Autowired
    private VaccinationCenterService vaccinationCenterService;
    @Autowired
    private VaccineService vaccineService;

    public Vaccination dtoToEntity(VaccinationDto vaccinationDto) {
        Vaccine vaccine = vaccineService.findByName(vaccinationDto.getVaccineName());
        VaccinationCenter vaccinationCenter = vaccinationCenterService.findByName(vaccinationDto.getVaccinationCenterName());
        if (vaccine!=null && vaccinationCenter!=null)
            return new Vaccination(vaccinationDto.getId(), vaccinationDto.getDate(), vaccinationDto.getPatientName(), vaccinationDto.getPassport(), vaccine, vaccinationCenter);
        return null;
    }

    public VaccinationDto entityToDto(Vaccination vaccination) {
        return new VaccinationDto(vaccination.getId(), vaccination.getDate(), vaccination.getPatientName(), vaccination.getPassport(), vaccination.getVaccine().getName(), vaccination.getVaccinationCenter().getName());
    }
}
