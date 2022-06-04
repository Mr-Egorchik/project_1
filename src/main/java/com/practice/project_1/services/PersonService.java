package com.practice.project_1.services;

import com.practice.project_1.entity.Person;
import com.practice.project_1.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    
    public void save(Person person) {
        personRepository.save(person);
    }

    public List<Person> findAll() {
        return (List<Person>) personRepository.findAll();
    }
    
    public Person findById(UUID uuid) {
        return personRepository.findById(uuid).orElseThrow();
    }

    public void delete(UUID uuid) {
        if (!personRepository.existsById(uuid)) {
            throw new NoSuchElementException("Person with this uuid is not found");
        }
        personRepository.deleteById(uuid);
    }

    public void update(UUID uuid, Person person) {
        if (!personRepository.existsById(uuid)) {
            throw new NoSuchElementException("Person with this uuid cannot be updated");
        }
        personRepository.save(person);
    }

    public void deleteAll() {
        personRepository.deleteAll();
    }

}
