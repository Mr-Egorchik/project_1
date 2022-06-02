package com.practice.project_1.services;

import com.practice.project_1.entity.Address;
import com.practice.project_1.entity.Person;
import com.practice.project_1.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void save(Address address) {
        addressRepository.save(address);
    }

    public Address findById(UUID uuid) {
        return addressRepository.findById(uuid).orElseThrow();
    }

    public void delete(UUID uuid) {
        if (!addressRepository.existsById(uuid)) {
            throw new NoSuchElementException("Person with this uuid is not found");
        }
        addressRepository.deleteById(uuid);
    }

    public void deleteAll() {
        addressRepository.deleteAll();
    }
}
