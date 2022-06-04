package com.practice.project_1;

import com.practice.project_1.entity.Address;
import com.practice.project_1.entity.Contact;
import com.practice.project_1.entity.Document;
import com.practice.project_1.entity.Person;
import com.practice.project_1.services.AddressService;
import com.practice.project_1.services.ContactService;
import com.practice.project_1.services.DocumentService;
import com.practice.project_1.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private DocumentService documentService;

    private static Logger log = Logger.getLogger(PersonController.class.getName());
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable(value = "id") UUID uuid) {
        try {
            log.info("Start GET/persons/" + uuid + " request");
            Person person = personService.findById(uuid);
            log.info("OK-response for GET/persons/" + uuid + " request");
            return ResponseEntity.ok().body(person);
        }
        catch (NoSuchElementException e) {
            log.info(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {
        log.info("Start POST/persons request");
        for (Address address: person.getAddresses()) {
            try {
                log.info("Try to find address with uuid " + address.getUuid());
                addressService.findById(address.getUuid());
                log.info("Address with such uuid is found");
            }
            catch (NoSuchElementException e) {
                log.info("Address with such uuid is not found");
                addressService.save(address);
                log.info("New address is saved");
            }
        }
        for (Contact contact: person.getContacts()) {
            contactService.save(contact);
            log.info("New contact is saved");
        }
        for (Document document: person.getDocuments()) {
            documentService.save(document);
            log.info("New document is saved");
        }
        personService.save(person);
        log.info("New person is saved");
        log.info("OK-response for POST/persons request");
        return ResponseEntity.ok().body(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable(value = "id") UUID uuid) {
        log.info("Start DELETE/persons" + uuid + " request");
        try {
            Person person = personService.findById(uuid);
            log.info("Delete contacts");
            for (Contact contact: person.getContacts())
                contactService.delete(contact.getUuid());
            log.info("Contacts are deleted");
            log.info("Delete documents");
            for (Document document: person.getDocuments())
                documentService.delete(document.getUuid());
            log.info("Documents are deleted");
            person.setAddresses(new ArrayList<>());
            personService.delete(uuid);
            log.info("Person is deleted");
            log.info("OK-response for DELETE/persons" + uuid + " request");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updPerson(@PathVariable(value = "id") UUID uuid, @RequestBody Person person) {
        log.info("Start PUT/persons" + uuid + " request");
        try {
            Person old = personService.findById(uuid);
            List<UUID> olds = new ArrayList<>();
            List<UUID> news = new ArrayList<>();
            for (Contact contact: old.getContacts())
                olds.add(contact.getUuid());
            for (Contact contact: person.getContacts()) {
                news.add(contact.getUuid());
                contact.setPerson(person);
                try {
                    contactService.findById(contact.getUuid());
                }
                catch (NoSuchElementException e) {
                    contactService.save(contact);
                }
            }
            for (UUID oldID: olds) {
                if (!news.contains(oldID))
                    contactService.delete(oldID);
            }
            log.info("Contacts are updated");
            olds.clear();
            news.clear();
            for (Document document: old.getDocuments())
                olds.add(document.getUuid());
            for (Document document: person.getDocuments()) {
                news.add(document.getUuid());
                document.setPerson(person);
                try {
                    documentService.findById(document.getUuid());
                }
                catch (NoSuchElementException e) {
                    documentService.save(document);
                }
            }
            for (UUID oldID: olds) {
                if (!news.contains(oldID))
                    documentService.delete(oldID);
            }
            log.info("Documents are updated");
            for (Address address: person.getAddresses()) {
                try {
                    addressService.findById(address.getUuid());
                }
                catch (NoSuchElementException e) {
                    addressService.save(address);
                }
            }
            log.info("Addresses are updated");
            personService.update(uuid, person);
            log.info("Person data is updated");
            log.info("OK-response for PUT/persons" + uuid + " request");
            return ResponseEntity.ok().body(person);
        }
        catch (NoSuchElementException e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Person>> getListOfPersons(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        log.info("Start GET/persons?pageNum=" + pageNum +"&pageSize=" + pageSize + " request");
        try {
            if (pageNum < 1 || pageSize < 1)
                throw new IllegalArgumentException("Incorrect request params");
            List<Person> all = personService.findAll();
            List<Person> res = new ArrayList<>();
            for (int i = (pageNum-1)*pageSize; i<pageNum*pageSize && i<all.size(); ++i)
                res.add(all.get(i));
            if (res.isEmpty())
                throw new IllegalArgumentException("Incorrect request params");
            log.info("OK-response for GET/persons?pageNum=" + pageNum +"&pageSize=" + pageSize + " request");
            return ResponseEntity.ok().body(res);
        }
        catch (IllegalArgumentException e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/verify")
    public ResponseEntity<Person> verifyPersonPassport (@RequestParam("name") UUID personId, @RequestParam("passport") UUID passportId) {
        log.info("Start GET/persons/verify?name=" + personId + "&passport=" + passportId + " request");
        try {
            Document document = documentService.findById(passportId);
            personService.findById(personId);
            if (!(document.getDocType().equals(Document.DocType.PASSPORT)))
                throw new IllegalArgumentException("It's not a passport");
            if (!(document.getPersonId().equals(personId)))
                throw new IllegalArgumentException("There's no such relationship");
            log.info("OK-response for GET/persons/verify?name=" + personId + "&passport=" + passportId + " request");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
