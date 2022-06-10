package ru.isys.practice.services;

import ru.isys.practice.entity.Contact;
import ru.isys.practice.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ContactService {

    @Autowired
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void save(Contact contact) {
        contactRepository.save(contact);
    }

    public Contact findById(UUID uuid) {
        return contactRepository.findById(uuid).orElse(null);
    }

    public void delete(UUID uuid) {
        if (!contactRepository.existsById(uuid)) {
            throw new NoSuchElementException("Person with this uuid is not found");
        }
        contactRepository.deleteById(uuid);
    }

    public void deleteAll() {
        contactRepository.deleteAll();
    }
}
