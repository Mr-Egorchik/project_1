package com.practice.project_1.mapping;

import com.practice.project_1.dto.AddressDto;
import com.practice.project_1.entity.Address;
import com.practice.project_1.entity.Person;
import com.practice.project_1.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AddressMapping {

    @Autowired
    private PersonService personService;

    public AddressDto entityToDto(Address address) {
        return new AddressDto(address.getUuid(), address.getAddType(), address.getCountry(), address.getCity(), address.getStreet(), address.getBuilding(), address.getPersons().stream().map(Person::getUuid).collect(Collectors.toList()));
    }

    public Address dtoToEntity(AddressDto addressDto) {
        List<Person> personList = new ArrayList<>();
        List<UUID> personUuids = addressDto.getPersonUuids();
        if (personUuids == null)
            personUuids = new ArrayList<>();
        for (UUID id : personUuids)
            personList.add(personService.findById(id));
        return new Address(addressDto.getUuid(), addressDto.getAddType(), addressDto.getCountry(), addressDto.getCity(), addressDto.getStreet(), addressDto.getBuilding(), personList);

    }
}
