package me.jungwirth.playground.springboot.search.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ArticleRepositoryTest {
    
    @Autowired
    private ArticleRepository articleRepository;
    
    @Test
    public void should_return_all_articles() {
        var articles = articleRepository.count();
        assertEquals(2, articles);

    }
}
