package me.jungwirth.playground.springboot.search.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import me.jungwirth.playground.springboot.search.dto.SearchRequest;
import me.jungwirth.playground.springboot.search.dto.SearchResponse;
import me.jungwirth.playground.springboot.search.model.Article;

@SpringBootTest
public class SearchServiceTest {
    

    @Autowired
    private SearchService searchService;


    @Test
    public void testSearchService() {

        SearchRequest request = new SearchRequest("Maßnahmen", "", null);
        SearchResponse<Article> result = searchService.searchQuery(request);

        assertEquals(2, result.getResult().size());
    
    }
}
