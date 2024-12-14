package me.jungwirth.playground.springboot.search.dto;

import java.util.List;
import org.springframework.data.web.PagedModel;


public class SearchResponse<T> {

    private List<T> result;

    private PagedModel.PageMetadata page;

    private List<SearchAggregation> abbreviations;

    public SearchResponse() {}

    public SearchResponse(List<T> result, PagedModel.PageMetadata page, List<SearchAggregation> aggregations) {
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

    public PagedModel.PageMetadata getPage() {
        return page;
    }

    public void setPage(PagedModel.PageMetadata page) {
        this.page = page;
    }

    public List<SearchAggregation> getAbbreviations() {
        return abbreviations;
    }

    public void setAbbreviations(List<SearchAggregation> abbreviations) {
        this.abbreviations = abbreviations;
    }
    
}
