package com.practice.project_1.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.practice.project_1.dto.ContactDto;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Contact")
public class Contact {
    public enum ContactType {PHONE, EMAIL, VK, TELEGRAM}

    @Id
    private UUID uuid;
    @Column(name = "contactType")
    private ContactType contactType;
    @Column(name = "info")
    private String info;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Contact() {
    }

    public Contact(UUID uuid, ContactType contactType, String info, Person person) {
        this.uuid = uuid;
        this.contactType = contactType;
        this.info = info;
        this.person = person;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getInfo() {
        return info;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
