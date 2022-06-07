package com.practice.project_1.dto;

import com.practice.project_1.entity.Address;
import com.practice.project_1.entity.Person;
import com.practice.project_1.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class AddressDto implements Serializable {
    private final UUID uuid;
    private final Address.AddType addType;
    private final String country;
    private final String city;
    private final String street;
    private final int building;
    private final List<UUID> personUuids;

    public AddressDto(UUID uuid, Address.AddType addType, String country, String city, String street, int building, List<UUID> personUuids) {
        this.uuid = uuid;
        this.addType = addType;
        this.country = country;
        this.city = city;
        this.street = street;
        this.building = building;
        this.personUuids = personUuids;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Address.AddType getAddType() {
        return addType;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getBuilding() {
        return building;
    }

    public List<UUID> getPersonUuids() {
        return personUuids;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDto entity = (AddressDto) o;
        return Objects.equals(this.uuid, entity.uuid) &&
                Objects.equals(this.addType, entity.addType) &&
                Objects.equals(this.country, entity.country) &&
                Objects.equals(this.city, entity.city) &&
                Objects.equals(this.street, entity.street) &&
                Objects.equals(this.building, entity.building) &&
                Objects.equals(this.personUuids, entity.personUuids);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, addType, country, city, street, building, personUuids);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "uuid = " + uuid + ", " +
                "addType = " + addType + ", " +
                "country = " + country + ", " +
                "city = " + city + ", " +
                "street = " + street + ", " +
                "building = " + building + ", " +
                "personUuids = " + personUuids + ")";
    }
}
