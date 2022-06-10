package ru.isys.practice.services;

import ru.isys.practice.entity.Document;
import ru.isys.practice.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class DocumentService {

    @Autowired
    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public void save(Document document) {
        documentRepository.save(document);
    }

    public Document findById(UUID uuid) {
        return documentRepository.findById(uuid).orElse(null);
    }

    public Document findByName(String name) {
        return documentRepository.findById(documentRepository.findByName(name)).orElse(null);
    }

    public void delete(UUID uuid) {
        if (!documentRepository.existsById(uuid)) {
            throw new NoSuchElementException("Document with this uuid is not found");
        }
        documentRepository.deleteById(uuid);
    }

    public void deleteAll() {
        documentRepository.deleteAll();
    }
}
