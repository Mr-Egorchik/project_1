package com.practice.project_1.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PersonDto implements Serializable {
    private final UUID uuid;
    private final String name;
    private final LocalDate date;
    private final List<DocumentDto> documents;
    private final List<AddressDto> addresses;
    private final List<ContactDto> contacts;

    public PersonDto(UUID uuid, String name, LocalDate date, List<DocumentDto> documents, List<AddressDto> addresses, List<ContactDto> contacts) {
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

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<DocumentDto> getDocuments() {
        return documents;
    }

    public List<AddressDto> getAddresses() {
        return addresses;
    }

    public List<ContactDto> getContacts() {
        return contacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDto entity = (PersonDto) o;
        return Objects.equals(this.uuid, entity.uuid) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.date, entity.date) &&
                Objects.equals(this.documents, entity.documents) &&
                Objects.equals(this.addresses, entity.addresses) &&
                Objects.equals(this.contacts, entity.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, date, documents, addresses, contacts);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "uuid = " + uuid + ", " +
                "name = " + name + ", " +
                "date = " + date + ", " +
                "documents = " + documents + ", " +
                "addresses = " + addresses + ", " +
                "contacts = " + contacts + ")";
    }
}
