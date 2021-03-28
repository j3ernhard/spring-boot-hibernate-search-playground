package me.jungwirth.playground.springboot.search.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.search.engine.search.aggregation.AggregationKey;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.engine.search.sort.dsl.SearchSortFactory;
import org.hibernate.search.engine.search.sort.dsl.SortOrder;
import org.hibernate.search.engine.search.sort.dsl.SortOrderStep;
import org.hibernate.search.engine.search.sort.spi.SearchSortBuilder;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.hateoas.PagedModel.PageMetadata;
import org.springframework.stereotype.Service;

import me.jungwirth.playground.springboot.search.dto.SearchAggregation;
import me.jungwirth.playground.springboot.search.dto.SearchRequest;
import me.jungwirth.playground.springboot.search.dto.SearchResponse;
import me.jungwirth.playground.springboot.search.model.Article;

@Service
public class SearchService {

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void initialize() {

        SearchSession searchSession = Search.session(entityManager.getEntityManagerFactory().createEntityManager());
        MassIndexer indexer = searchSession.massIndexer(Article.class).threadsToLoadObjects(2);

        try {
            indexer.startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * 
     * @param request
     * @return
     */
    @Transactional
    public SearchResponse<Article> searchTitleQuery(SearchRequest request) {

        SearchSession searchSession = Search.session(entityManager);
        SearchResult<Article> result = searchSession.search(Article.class)
            .where(f -> f.match().fields("document.title", "document.abbreviation")
                .matching(request.keyword))
            .fetch(20);

        return new SearchResponse<>(result.hits(), new PageMetadata(20, 0, result.total().hitCount()), null);
    }

    /**
     * 
     * @param request
     * @return
     */
    @Transactional
    public SearchResponse<Article> searchTitleQueryPagination(SearchRequest request) {

        SearchSession searchSession = Search.session(entityManager);
        SearchResult<Article> result = searchSession.search(Article.class)
            .where(f -> f.match().fields("document.title", "document.abbreviation")
                .matching(request.keyword))
            .fetch(
                request.page.getPageNumber() * request.page.getPageSize(),
                request.page.getPageSize()
            );

        return new SearchResponse<>(
            result.hits(), 
            new PageMetadata(request.page.getPageSize(), request.page.getPageNumber(), result.total().hitCount()),
            null);
    }

    @Transactional
    public SearchResponse<Article> searchTitleQueryPaginationAndSort(SearchRequest request) {

        SearchSession searchSession = Search.session(entityManager);
        SearchResult<Article> result = searchSession.search(Article.class)
            .where(f -> f.match().fields("document.title", "document.abbreviation")
                .matching(request.keyword))
            .sort(f -> f.field("title_sort").desc())
            .fetch(
                request.page.getPageNumber() * request.page.getPageSize(),
                request.page.getPageSize()
            );

        return new SearchResponse<>(
            result.hits(), 
            new PageMetadata(request.page.getPageSize(), request.page.getPageNumber(), result.total().hitCount()),
            null);
    }

    @Transactional
    public SearchResponse<Article> searchQueryAggregation(SearchRequest request) {
        SearchSession searchSession = Search.session( entityManager );

        AggregationKey<Map<String, Long>> countByAbbreviation = AggregationKey.of("countByAbbreviation");

        SearchResult<Article> result = searchSession.search(Article.class)
            .where(f -> f.match().fields("document.title", "document.abbreviation")
                .matching(request.keyword))
            .aggregation(countByAbbreviation, f -> f.terms().field("document.abbreviation", String.class))
            .fetch(20);


        List<SearchAggregation> facetAbbreviation = result.aggregation(countByAbbreviation)
            .entrySet()
            .stream()
            .map(e -> new SearchAggregation(e.getKey(), e.getValue()))
            .collect(Collectors.toList());
        SearchResponse<Article> response = new SearchResponse<>(result.hits(), null, facetAbbreviation);
        
        return response;
    }

    /**
     * method with all search
     * apply aggregation 
     */
    @Transactional
    public SearchResponse<Article> searchQuery(SearchRequest request) {
        SearchSession searchSession = Search.session( entityManager );

        AggregationKey<Map<String, Long>> countByAbbreviation = AggregationKey.of("countByAbbreviation");

        SearchResult<Article> result = searchSession.search(Article.class)
            .where(f -> f.match().fields("title", "document.title", "document.abbreviation")
                .matching(request.keyword))
            .sort(f -> f.composite( b -> {
                if(request.page != null)  {
                    request.page.getSort().get().forEach(s -> {
                        b.add(f.field(s.getProperty()).order(s.isAscending() ? SortOrder.ASC : SortOrder.DESC));
                    });
                }
            }))
            .aggregation(countByAbbreviation, f -> f.terms().field("document.abbreviation", String.class))
            .fetch(
                request.page.getPageNumber() * request.page.getPageSize(),
                request.page.getPageSize()
            );


        List<SearchAggregation> facetAbbreviation = result.aggregation(countByAbbreviation)
            .entrySet()
            .stream()
            .map(e -> new SearchAggregation(e.getKey(), e.getValue()))
            .collect(Collectors.toList());
        SearchResponse<Article> response = new SearchResponse<>(
            result.hits(), 
            new PageMetadata(request.page.getPageSize(), request.page.getPageNumber(), result.total().hitCount()),
            facetAbbreviation);
        
        return response;
    }
}
