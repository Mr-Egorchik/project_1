package ru.isys.practice.repositories;

import ru.isys.practice.entity.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ContactRepository extends CrudRepository<Contact, UUID> {
}