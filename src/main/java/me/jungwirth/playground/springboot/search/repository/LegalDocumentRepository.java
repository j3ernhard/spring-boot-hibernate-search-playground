package me.jungwirth.playground.springboot.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import me.jungwirth.playground.springboot.search.model.LegalDocument;

@RepositoryRestResource
public interface LegalDocumentRepository extends JpaRepository<LegalDocument,Long> {
    
}
