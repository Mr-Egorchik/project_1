package ru.isys.practice.repositories;

import ru.isys.practice.entity.Document;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface DocumentRepository extends CrudRepository<Document, UUID> {
    @Query("select id from Document where info=:name")
    UUID findByName(@Param("name") String name);
}
