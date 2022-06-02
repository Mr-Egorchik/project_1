package com.practice.project_1.repositories;

import com.practice.project_1.entity.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PersonRepository extends CrudRepository<Person, UUID> {
}
