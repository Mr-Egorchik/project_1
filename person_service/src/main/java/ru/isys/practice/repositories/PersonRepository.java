package ru.isys.practice.repositories;

import ru.isys.practice.entity.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface PersonRepository extends PagingAndSortingRepository<Person, UUID> {
}
