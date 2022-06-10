package ru.isys.practice.repositories;

import ru.isys.practice.entity.VaccinationCenter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface VaccinationCenterRepository extends CrudRepository<VaccinationCenter, UUID> {
    @Query("select vc from VaccinationCenter vc where name=:name")
    VaccinationCenter findByName(@Param("name") String name);
}