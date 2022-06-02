package com.practice.project_1;

import com.practice.project_1.entity.Person;
import com.practice.project_1.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable(value = "id") UUID uuid) {
        try {
            Person person = personService.findById(uuid);
            return ResponseEntity.ok().body(person);
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {
        personService.save(person);
        return ResponseEntity.ok().body(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable(value = "id") UUID uuid) {
        try {
            personService.delete(uuid);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updPerson(@PathVariable(value = "id") UUID uuid, @RequestBody Person person) {
        try {
            personService.update(uuid, person);
            return ResponseEntity.ok().body(person);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
