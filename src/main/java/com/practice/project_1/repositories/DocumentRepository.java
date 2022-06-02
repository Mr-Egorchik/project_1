package com.practice.project_1.repositories;

import com.practice.project_1.entity.Document;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DocumentRepository extends CrudRepository<Document, UUID> {
}
