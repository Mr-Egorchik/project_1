package com.practice.project_1.mapping;

import com.practice.project_1.dto.AddressDto;
import com.practice.project_1.dto.ContactDto;
import com.practice.project_1.dto.DocumentDto;
import com.practice.project_1.dto.PersonDto;
import com.practice.project_1.entity.Address;
import com.practice.project_1.entity.Contact;
import com.practice.project_1.entity.Document;
import com.practice.project_1.entity.Person;
import com.practice.project_1.services.AddressService;
import com.practice.project_1.services.ContactService;
import com.practice.project_1.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
