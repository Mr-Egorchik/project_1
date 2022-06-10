package ru.isys.practice.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class AddressDto implements Serializable {
    public enum AddressType {RESIDENTIAL, REGISTRATION, POSTAL}
    private final UUID uuid;
    private final AddressType addressType;
    private final String country;
    private final String city;
    private final String street;
    private final int building;
    private final List<UUID> personUuids;

    public AddressDto(UUID uuid, AddressType addressType, String country, String city, String street, int building, List<UUID> personUuids) {
        this.uuid = uuid;
        this.addressType = addressType;
        this.country = country;
        this.city = city;
        this.street = street;
        this.building = building;
        this.personUuids = personUuids;
    }

    public UUID getUuid() {
        return uuid;
    }

    public AddressType getAddType() {
        return addressType;
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
                Objects.equals(this.addressType, entity.addressType) &&
                Objects.equals(this.country, entity.country) &&
                Objects.equals(this.city, entity.city) &&
                Objects.equals(this.street, entity.street) &&
                Objects.equals(this.building, entity.building) &&
                Objects.equals(this.personUuids, entity.personUuids);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, addressType, country, city, street, building, personUuids);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "uuid = " + uuid + ", " +
                "addType = " + addressType + ", " +
                "country = " + country + ", " +
                "city = " + city + ", " +
                "street = " + street + ", " +
                "building = " + building + ", " +
                "personUuids = " + personUuids + ")";
    }
}
