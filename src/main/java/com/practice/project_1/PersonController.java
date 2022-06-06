package com.practice.project_1;

import com.practice.project_1.dto.PersonDto;
import com.practice.project_1.entity.Address;
import com.practice.project_1.entity.Contact;
import com.practice.project_1.entity.Document;
import com.practice.project_1.entity.Person;
import com.practice.project_1.services.AddressService;
import com.practice.project_1.services.ContactService;
import com.practice.project_1.services.DocumentService;
import com.practice.project_1.services.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

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

    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getPerson(@PathVariable(value = "id") UUID uuid) {
        log.info("Start GET/persons/" + uuid + " request");
        Person person = personService.findById(uuid);
        if (person == null) {
            log.info("NOT_FOUND-response for GET/persons/" + uuid + " request");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("OK-response for GET/persons/" + uuid + " request");
        return ResponseEntity.ok(person.toDto());
    }

    @PostMapping("")
    public ResponseEntity<PersonDto> savePerson(@RequestBody PersonDto personDto) {
        log.info("Start POST/persons request");
        personService.save(personDto.toEntity());
        log.info("New person is saved");
        log.info("OK-response for POST/persons request");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable(value = "id") UUID uuid) {
        log.info("Start DELETE/persons" + uuid + " request");
        personService.delete(uuid);
        log.info("Person is deleted");
        log.info("OK-response for DELETE/persons" + uuid + " request");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updPerson(@PathVariable(value = "id") UUID uuid, @RequestBody Person person) {
        log.info("Start PUT/persons" + uuid + " request");
        personService.update(uuid, person);
        log.info("Person data is updated");
        log.info("OK-response for PUT/persons" + uuid + " request");
        return ResponseEntity.ok().body(person);
    }

    @GetMapping("")
    public ResponseEntity<List<PersonDto>> getListOfPersons(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        log.info("Start GET/persons?pageNum=" + pageNum + "&pageSize=" + pageSize + " request");
        try {
            if (pageNum < 1 || pageSize < 1)
                throw new IllegalArgumentException("Incorrect request params");
            Pageable page = PageRequest.of(pageNum - 1, pageSize);
            List<PersonDto> personDtoList = new ArrayList<>();
            for (Person person : personService.findAll(page))
                personDtoList.add(person.toDto());
            return ResponseEntity.ok().body(personDtoList);
        } catch (IllegalArgumentException e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/verify")
    public ResponseEntity<Person> verifyPersonPassport(@RequestParam("name") String person, @RequestParam("passport") String passport) {
        log.info("Start GET/persons/verify?name=" + person + "&passport=" + passport + " request");
        Document document = documentService.findByName(passport);
        if (document != null && document.getDocType().equals(Document.DocType.PASSPORT) && document.getPerson().getName().equals(person))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
