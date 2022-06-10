package ru.isys.practice.entity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Vaccine")
public class Vaccine {
    @Id
    private UUID id;
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "vaccine")
    private List<Vaccination> vaccinations;

    public List<Vaccination> getVaccinations() {
        return vaccinations;
    }

    public void listVaccinations(List<Vaccination> vaccinations) {
        this.vaccinations = vaccinations;
    }

    public Vaccine() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}