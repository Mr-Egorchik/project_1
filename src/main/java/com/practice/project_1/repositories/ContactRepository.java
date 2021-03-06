package com.practice.project_1.repositories;

import com.practice.project_1.entity.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ContactRepository extends CrudRepository<Contact, UUID> {
}