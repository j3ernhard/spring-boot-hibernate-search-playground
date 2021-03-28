package me.jungwirth.playground.springboot.search.dto;

import java.util.List;

import org.springframework.hateoas.PagedModel.PageMetadata;

public class SearchResponse<T> {

    private List<T> result;

    private PageMetadata page;

    private List<SearchAggregation> abbreviations;

    public SearchResponse() {}

    public SearchResponse(List<T> result, PageMetadata page, List<SearchAggregation> aggregations) {
        this.result = result;
        this.page = page;
        this.abbreviations = aggregations;
    }
    
    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public PageMetadata getPage() {
        return page;
    }

    public void setPage(PageMetadata page) {
        this.page = page;
    }

    public List<SearchAggregation> getAbbreviations() {
        return abbreviations;
    }

    public void setAbbreviations(List<SearchAggregation> abbreviations) {
        this.abbreviations = abbreviations;
    }
    
}
