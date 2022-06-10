package ru.isys.practice.repositories;

import ru.isys.practice.entity.Vaccine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface VaccineRepository extends CrudRepository<Vaccine, UUID> {
    @Query("select v from Vaccine v where name=:name")
    Vaccine findByName(@Param("name") String name);
}