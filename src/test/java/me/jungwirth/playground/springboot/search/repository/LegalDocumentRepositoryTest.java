package me.jungwirth.playground.springboot.search.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class LegalDocumentRepositoryTest {

    @Autowired
    private LegalDocumentRepository legalDocumentRepository;

    @Test
    public void should_return_all_documents() {
        var documents = legalDocumentRepository.count();
        assertEquals(1, documents);

    }
    
}
