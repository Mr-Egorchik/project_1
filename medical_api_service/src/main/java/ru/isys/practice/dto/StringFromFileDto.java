package ru.isys.practice.dto;

import java.io.Serializable;
import java.util.Objects;

public class StringFromFileDto implements Serializable {
    private final String idString;
    private final String name;
    private final String passport;
    private final String dateString;
    private final String vaccineName;
    private final String vaccinationCenterName;

    public StringFromFileDto(String[] params) {
        idString = params[0].substring(1);
        name = params[1];
        passport = new StringBuilder(params[2].replaceAll(" ", "")).insert(4, " ").toString();
        dateString = params[3];
        vaccineName = params[4];
        vaccinationCenterName = params[5];
    }

    public String getIdString() {
        return idString;
    }

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
    }

    public String getDateString() {
        return dateString;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public String getVaccinationCenterName() {
        return vaccinationCenterName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringFromFileDto that = (StringFromFileDto) o;
        return idString.equals(that.idString) && name.equals(that.name) && passport.equals(that.passport) && dateString.equals(that.dateString) && vaccineName.equals(that.vaccineName) && vaccinationCenterName.equals(that.vaccinationCenterName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idString, name, passport, dateString, vaccineName, vaccinationCenterName);
    }

    @Override
    public String toString() {
        return "StringFromFileDto{" +
                "idString='" + idString + '\'' +
                ", name='" + name + '\'' +
                ", passport='" + passport + '\'' +
                ", dateString='" + dateString + '\'' +
                ", vaccineName='" + vaccineName + '\'' +
                ", vaccinationCenterName='" + vaccinationCenterName + '\'' +
                '}';
    }
}
