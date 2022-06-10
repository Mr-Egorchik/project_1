package ru.isys.practice.repositories;

import ru.isys.practice.entity.Vaccination;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface VaccinationRepository extends CrudRepository<Vaccination, UUID> {
    @Query("select v from Vaccination v where passport=:passport")
    List<Vaccination> getByPassport(String passport);
}