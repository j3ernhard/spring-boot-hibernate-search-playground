package me.jungwirth.playground.springboot.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.jungwirth.playground.springboot.search.dto.SearchRequest;
import me.jungwirth.playground.springboot.search.dto.SearchResponse;
import me.jungwirth.playground.springboot.search.model.Article;
import me.jungwirth.playground.springboot.search.service.SearchService;

@RestController
@RequestMapping("services")
public class SearchController {
    
    @Autowired
    private SearchService searchService;

    @GetMapping("search")
    public SearchResponse<Article> search(@RequestParam("q") String query, Pageable page) {

        SearchRequest request = new SearchRequest(query, null, null, page);

        SearchResponse<Article> response = searchService.searchQuery(request);

        return response;
    }
}
