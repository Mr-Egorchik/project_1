package com.practice.project_1.repositories;

import com.practice.project_1.entity.Vaccination;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface VaccinationRepository extends CrudRepository<Vaccination, UUID> {
    @Query("select id from Vaccination where passport=:passport")
    List<UUID> getByPassport(String passport);
}