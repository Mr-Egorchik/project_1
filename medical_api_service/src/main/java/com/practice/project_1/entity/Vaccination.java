package com.practice.project_1.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "vaccination")
public class Vaccination {
    @Id
    private UUID id;

    @Column(name = "date", nullable = false)
    private LocalDate date;


    @Column(name = "patient_name", nullable = false)
    private String patientName;

    @Column(name = "passport", nullable = false)
    private String passport;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vaccine_id", nullable = false)
    private Vaccine vaccine;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vaccination_center_id", nullable = false)
    private VaccinationCenter vaccinationCenter;

    public Vaccination() {
    }

    public Vaccination(UUID id, LocalDate date, String patientName, String passport, Vaccine vaccine, VaccinationCenter vaccinationCenter) {
        this.id = id;
        this.date = date;
        this.patientName = patientName;
        this.passport = passport;
        this.vaccine = vaccine;
        this.vaccinationCenter = vaccinationCenter;
    }

    public VaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }

    public void setVaccinationCenter(VaccinationCenter vaccinationCenter) {
        this.vaccinationCenter = vaccinationCenter;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}