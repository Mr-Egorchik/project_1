package ru.isys.practice.entity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Address")
public class Address {
    public enum AddType {RESIDENTIAL, REGISTRATION, POSTAL}

    @Id
    private UUID uuid;
    @Column(name = "addressType")
    private AddType addType;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "building", nullable = false)
    private int building;
    @ManyToMany(mappedBy = "addresses")
    private List<Person> persons;

    public Address() {
    }

    public Address(UUID uuid, AddType addType, String country, String city, String street, int building, List<Person> persons) {
        this.uuid = uuid;
        this.addType = addType;
        this.country = country;
        this.city = city;
        this.street = street;
        this.building = building;
        this.persons = persons;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public AddType getAddType() {
        return addType;
    }

    public void setAddType(AddType addType) {
        this.addType = addType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuilding() {
        return building;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
