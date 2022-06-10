package ru.isys.practice.mapping;

import ru.isys.practice.dto.DocumentDto;
import ru.isys.practice.entity.Document;
import ru.isys.practice.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentMapping {

    @Autowired
    private PersonService personService;

    public Document dtoToEntity(DocumentDto documentDto) {
        return new Document(documentDto.getUuid(), documentDto.getDocType(), documentDto.getInfo(), personService.findById(documentDto.getPersonUuid()));
    }

    public DocumentDto entityToDto(Document document) {
        return new DocumentDto(document.getUuid(), document.getDocType(), document.getInfo(), document.getPerson().getUuid());
    }
}
