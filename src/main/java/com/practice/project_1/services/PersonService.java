package com.practice.project_1.services;

import com.practice.project_1.dto.AddressDto;
import com.practice.project_1.dto.ContactDto;
import com.practice.project_1.dto.DocumentDto;
import com.practice.project_1.dto.PersonDto;
import com.practice.project_1.entity.Address;
import com.practice.project_1.entity.Contact;
import com.practice.project_1.entity.Document;
import com.practice.project_1.entity.Person;
import com.practice.project_1.mapping.AddressMapping;
import com.practice.project_1.mapping.ContactMapping;
import com.practice.project_1.mapping.DocumentMapping;
import com.practice.project_1.mapping.PersonMapping;
import com.practice.project_1.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final AddressService addressService;
    private final ContactService contactService;
    private final DocumentService documentService;
    private final AddressMapping addressMapping;
    private final ContactMapping contactMapping;
    private final DocumentMapping documentMapping;
    private final PersonMapping personMapping;

    @Autowired
    public PersonService(PersonRepository personRepository, AddressService addressService, ContactService contactService, DocumentService documentService, @Lazy AddressMapping addressMapping, @Lazy ContactMapping contactMapping, @Lazy DocumentMapping documentMapping, @Lazy PersonMapping personMapping) {
        this.personRepository = personRepository;
        this.addressService = addressService;
        this.contactService = contactService;
        this.documentService = documentService;
        this.addressMapping = addressMapping;
        this.contactMapping = contactMapping;
        this.documentMapping = documentMapping;
        this.personMapping = personMapping;
    }

    public void save(PersonDto person) {
        for (AddressDto address : person.getAddresses())
            if (addressService.findById(address.getUuid()) == null)
                addressService.save(addressMapping.dtoToEntity(address));
        for (ContactDto contact : person.getContacts())
            contactService.save(contactMapping.dtoToEntity(contact));
        for (DocumentDto document : person.getDocuments())
            documentService.save(documentMapping.dtoToEntity(document));
        personRepository.save(personMapping.dtoToEntity(person));
        Person person1 = findById(person.getUuid());
        for (Document document: person1.getDocuments()) {
            document.setPerson(person1);
            documentService.save(document);
        }
        for (Contact contact: person1.getContacts()) {
            contact.setPerson(person1);
            contactService.save(contact);
        }
    }

    public List<Person> findAll() {
        return (List<Person>) personRepository.findAll();
    }

    public List<Person> findAll(Pageable pageable) {
        return personRepository.findAll(pageable).getContent();
    }

    public Person findById(UUID uuid) {
        return personRepository.findById(uuid).orElse(null);
    }

    public void delete(UUID uuid) {
        if (personRepository.existsById(uuid)) {
            Person person = findById(uuid);
            for (Contact contact : person.getContacts())
                contactService.delete(contact.getUuid());
            for (Document document : person.getDocuments())
                documentService.delete(document.getUuid());
            person.setAddresses(new ArrayList<>());
            personRepository.deleteById(uuid);
        }
    }

    public void update(UUID uuid, PersonDto person) {
        if (personRepository.existsById(uuid)) {
            Person old = findById(uuid);
            List<UUID> olds = new ArrayList<>();
            List<UUID> news = new ArrayList<>();
            for (Contact contact : old.getContacts())
                olds.add(contact.getUuid());
            for (ContactDto contact : person.getContacts()) {
                news.add(contact.getUuid());
                if (contactService.findById(contact.getUuid()) == null)
                    contactService.save(contactMapping.dtoToEntity(contact));
            }
            for (UUID oldID : olds) {
                if (!news.contains(oldID))
                    contactService.delete(oldID);
            }
            olds.clear();
            news.clear();
            for (Document document : old.getDocuments())
                olds.add(document.getUuid());
            for (DocumentDto document : person.getDocuments()) {
                news.add(document.getUuid());
                if (documentService.findById(document.getUuid()) == null)
                    documentService.save(documentMapping.dtoToEntity(document));
            }
            for (UUID oldID : olds) {
                if (!news.contains(oldID))
                    documentService.delete(oldID);
            }
            for (AddressDto address : person.getAddresses()) {
                if (addressService.findById(address.getUuid()) == null)
                    addressService.save(addressMapping.dtoToEntity(address));
            }
            personRepository.save(personMapping.dtoToEntity(person));
        }
    }

    public void deleteAll() {
        personRepository.deleteAll();
    }

}
