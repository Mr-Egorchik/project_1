package com.practice.project_1.repositories;

import com.practice.project_1.entity.Vaccine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface VaccineRepository extends CrudRepository<Vaccine, UUID> {
    @Query("select id from Vaccine where name=:name")
    UUID findByName(@Param("name") String name);
}