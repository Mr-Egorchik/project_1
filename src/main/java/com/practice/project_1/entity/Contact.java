package com.practice.project_1.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Contact")
public class Contact {
    public enum ContactType {PHONE, EMAIL, VK, TELEGRAM}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    @Column(name = "info")
    private String info;
    @ManyToOne
    private Person person;

    public Contact() {
    }

    public Contact(UUID uuid, String info, Person person) {
        this.uuid = uuid;
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
