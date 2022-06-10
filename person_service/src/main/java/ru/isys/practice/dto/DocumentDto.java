package ru.isys.practice.dto;

import ru.isys.practice.entity.Document;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class DocumentDto implements Serializable {
    private final UUID uuid;
    private final Document.DocType docType;
    private final String info;
    private final UUID personUuid;

    public DocumentDto(UUID uuid, Document.DocType docType, String info, UUID personUuid) {
        this.uuid = uuid;
        this.docType = docType;
        this.info = info;
        this.personUuid = personUuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Document.DocType getDocType() {
        return docType;
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
        DocumentDto entity = (DocumentDto) o;
        return Objects.equals(this.uuid, entity.uuid) &&
                Objects.equals(this.docType, entity.docType) &&
                Objects.equals(this.info, entity.info) &&
                Objects.equals(this.personUuid, entity.personUuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, docType, info, personUuid);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "uuid = " + uuid + ", " +
                "docType = " + docType + ", " +
                "info = " + info + ", " +
                "personUuid = " + personUuid + ")";
    }
}
