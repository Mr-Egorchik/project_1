package ru.isys.practice.mapping;

import ru.isys.practice.dto.ContactDto;
import ru.isys.practice.entity.Contact;
import ru.isys.practice.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactMapping {
    @Autowired
    private PersonService personService;

    public Contact dtoToEntity(ContactDto contactDto) {
        return new Contact(contactDto.getUuid(), contactDto.getContactType(), contactDto.getInfo(), personService.findById(contactDto.getPersonUuid()));
    }

    public ContactDto entityToDto(Contact contact) {
        return new ContactDto(contact.getUuid(), contact.getContactType(), contact.getInfo(), contact.getPerson().getUuid());
    }
}
