package com.practice.project_1.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class VaccinationDto implements Serializable {
    private final UUID id;
    private final LocalDate date;
    private final String patientName;
    private final String passport;
    private final String vaccineName;
    private final String vaccinationCenterName;

    public VaccinationDto(UUID id, LocalDate date, String patientName, String passport, String vaccineName, String vaccinationCenterName) {
        this.id = id;
        this.date = date;
        this.patientName = patientName;
        this.passport = passport;
        this.vaccineName = vaccineName;
        this.vaccinationCenterName = vaccinationCenterName;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPassport() {
        return passport;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public String getVaccinationCenterName() {
        return vaccinationCenterName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VaccinationDto entity = (VaccinationDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.date, entity.date) &&
                Objects.equals(this.patientName, entity.patientName) &&
                Objects.equals(this.passport, entity.passport) &&
                Objects.equals(this.vaccineName, entity.vaccineName) &&
                Objects.equals(this.vaccinationCenterName, entity.vaccinationCenterName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, patientName, passport, vaccineName, vaccinationCenterName);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "date = " + date + ", " +
                "patientName = " + patientName + ", " +
                "passport = " + passport + ", " +
                "vaccineName = " + vaccineName + ", " +
                "vaccinationCenterName = " + vaccinationCenterName + ")";
    }
}
