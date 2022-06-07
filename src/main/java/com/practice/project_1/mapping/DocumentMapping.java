package com.practice.project_1.mapping;

import com.practice.project_1.dto.DocumentDto;
import com.practice.project_1.entity.Document;
import com.practice.project_1.entity.Person;
import com.practice.project_1.services.PersonService;
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
