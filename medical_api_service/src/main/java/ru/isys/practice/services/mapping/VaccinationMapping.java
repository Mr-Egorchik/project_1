package ru.isys.practice.services.mapping;

import ru.isys.practice.dto.VaccinationDto;
import ru.isys.practice.entity.Vaccination;
import ru.isys.practice.entity.VaccinationCenter;
import ru.isys.practice.entity.Vaccine;
import ru.isys.practice.services.VaccinationCenterService;
import ru.isys.practice.services.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationMapping {

    public Vaccination dtoToEntity(VaccinationDto vaccinationDto, Vaccine vaccine, VaccinationCenter vaccinationCenter) {
        return new Vaccination(vaccinationDto.getId(), vaccinationDto.getDate(), vaccinationDto.getPatientName(), vaccinationDto.getPassport(), vaccine, vaccinationCenter);
    }

    public VaccinationDto entityToDto(Vaccination vaccination) {
        return new VaccinationDto(vaccination.getId(), vaccination.getDate(), vaccination.getPatientName(), vaccination.getPassport(), vaccination.getVaccine().getName(), vaccination.getVaccinationCenter().getName());
    }
}
