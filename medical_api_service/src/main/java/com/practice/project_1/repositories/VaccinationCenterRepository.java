package com.practice.project_1.repositories;

import com.practice.project_1.entity.VaccinationCenter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface VaccinationCenterRepository extends CrudRepository<VaccinationCenter, UUID> {
    @Query("select id from VaccinationCenter where name=:name")
    UUID findByName(@Param("name") String name);
}