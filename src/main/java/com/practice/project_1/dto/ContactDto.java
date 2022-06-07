package com.practice.project_1.dto;

import com.practice.project_1.entity.Contact;
import com.practice.project_1.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class ContactDto implements Serializable {
    private final UUID uuid;
    private final Contact.ContactType contactType;
    private final String info;
    private final UUID personUuid;

    public ContactDto(UUID uuid, Contact.ContactType contactType, String info, UUID personUuid) {
        this.uuid = uuid;
        this.contactType = contactType;
        this.info = info;
        this.personUuid = personUuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Contact.ContactType getContactType() {
        return contactType;
    }

    public String getInfo() {
        return info;
    }

    public UUID getPersonUuid() {
        return personUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactDto entity = (ContactDto) o;
        return Objects.equals(this.uuid, entity.uuid) &&
                Objects.equals(this.contactType, entity.contactType) &&
                Objects.equals(this.info, entity.info) &&
                Objects.equals(this.personUuid, entity.personUuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, contactType, info, personUuid);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "uuid = " + uuid + ", " +
                "contactType = " + contactType + ", " +
                "info = " + info + ", " +
                "personUuid = " + personUuid + ")";
    }
}
