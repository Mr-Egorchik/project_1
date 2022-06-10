package ru.isys.practice.mapping;

import ru.isys.practice.dto.AddressDto;
import ru.isys.practice.dto.ContactDto;
import ru.isys.practice.dto.DocumentDto;
import ru.isys.practice.dto.PersonDto;
import ru.isys.practice.entity.Address;
import ru.isys.practice.entity.Contact;
import ru.isys.practice.entity.Document;
import ru.isys.practice.entity.Person;
import ru.isys.practice.services.AddressService;
import ru.isys.practice.services.ContactService;
import ru.isys.practice.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonMapping {

    @Autowired
    private DocumentService documentService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private AddressMapping addressMapping;
    @Autowired
    private ContactMapping contactMapping;
    @Autowired
    private DocumentMapping documentMapping;

    public Person dtoToEntity(PersonDto personDto) {
        List<Document> documentList = new ArrayList<>();
        List<Address> addressList = new ArrayList<>();
        List<Contact> contactList = new ArrayList<>();
        for (DocumentDto dto : personDto.getDocuments())
            documentList.add(documentService.findById(dto.getUuid()));
        for (AddressDto dto : personDto.getAddresses())
            addressList.add(addressService.findById(dto.getUuid()));
        for (ContactDto dto : personDto.getContacts())
            contactList.add(contactService.findById(dto.getUuid()));
        return new Person(personDto.getUuid(), personDto.getName(), personDto.getDate(), documentList, addressList, contactList);
    }

    public PersonDto entityToDto(Person person) {
        List<DocumentDto> documentDtoList = new ArrayList<>();
        List<AddressDto> addressDtoList = new ArrayList<>();
        List<ContactDto> contactDtoList = new ArrayList<>();
        for (Document document: person.getDocuments())
            documentDtoList.add(documentMapping.entityToDto(document));
        for (Address address: person.getAddresses())
            addressDtoList.add(addressMapping.entityToDto(address));
        for (Contact contact: person.getContacts())
            contactDtoList.add(contactMapping.entityToDto(contact));
        return new PersonDto(person.getUuid(), person.getName(), person.getDate(), documentDtoList, addressDtoList, contactDtoList);
    }
}
