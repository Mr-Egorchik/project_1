package com.practice.project_1.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.practice.project_1.dto.DocumentDto;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Document")
public class Document {
    public enum DocType {PASSPORT, INT_PASSPORT, SNILS, INSURANCE_POLICY, DRIVERS_LICENCE}

    @Id
    private UUID uuid;
    @Column(name = "docType", nullable = false)
    private DocType docType;
    @Column(name = "info", nullable = false)
    private String info;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Document() {
    }

    public Document(UUID uuid, DocType docType, String info, Person person) {
        this.uuid = uuid;
        this.docType = docType;
        this.info = info;
        this.person = person;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public DocType getDocType() {
        return docType;
    }

    public void setDocType(DocType docType) {
        this.docType = docType;
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

    public DocumentDto toDto() {
        return new DocumentDto(uuid, docType, info, person.getUuid());
    }
}
