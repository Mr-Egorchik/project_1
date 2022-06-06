package com.practice.project_1.services;

import com.practice.project_1.entity.Address;
import com.practice.project_1.entity.Contact;
import com.practice.project_1.entity.Document;
import com.practice.project_1.entity.Person;
import com.practice.project_1.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private DocumentService documentService;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void save(Person person) {
        for (Address address : person.getAddresses())
            if (addressService.findById(address.getUuid()) == null)
                addressService.save(address);
        for (Contact contact : person.getContacts())
            contactService.save(contact);
        for (Document document : person.getDocuments())
            documentService.save(document);
        personRepository.save(person);
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

    public void update(UUID uuid, Person person) {
        if (personRepository.existsById(uuid)) {
            Person old = findById(uuid);
            List<UUID> olds = new ArrayList<>();
            List<UUID> news = new ArrayList<>();
            for (Contact contact : old.getContacts())
                olds.add(contact.getUuid());
            for (Contact contact : person.getContacts()) {
                news.add(contact.getUuid());
                contact.setPerson(person);
                if (contactService.findById(contact.getUuid()) == null)
                    contactService.save(contact);
            }
            for (UUID oldID : olds) {
                if (!news.contains(oldID))
                    contactService.delete(oldID);
            }
            olds.clear();
            news.clear();
            for (Document document : old.getDocuments())
                olds.add(document.getUuid());
            for (Document document : person.getDocuments()) {
                news.add(document.getUuid());
                document.setPerson(person);
                if (documentService.findById(document.getUuid()) == null)
                    documentService.save(document);
            }
            for (UUID oldID : olds) {
                if (!news.contains(oldID))
                    documentService.delete(oldID);
            }
            for (Address address : person.getAddresses()) {
                if (addressService.findById(address.getUuid()) == null)
                    addressService.save(address);
            }
            personRepository.save(person);
        }
    }

    public void deleteAll() {
        personRepository.deleteAll();
    }

}
