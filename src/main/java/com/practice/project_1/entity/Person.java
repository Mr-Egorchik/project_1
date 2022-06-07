package com.practice.project_1.entity;

import com.practice.project_1.dto.PersonDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    private UUID uuid;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "birthday", nullable = false)
    private LocalDate date;
    @OneToMany(mappedBy = "person")
    private List<Document> documents;
    @ManyToMany
    private List<Address> addresses;
    @OneToMany(mappedBy = "person")
    private List<Contact> contacts;

    public Person() {
    }

    public Person(UUID uuid, String name, LocalDate date, List<Document> documents, List<Address> addresses, List<Contact> contacts) {
        this.uuid = uuid;
        this.name = name;
        this.date = date;
        this.documents = documents;
        this.addresses = addresses;
        this.contacts = contacts;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
