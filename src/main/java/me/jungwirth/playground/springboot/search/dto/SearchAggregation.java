package me.jungwirth.playground.springboot.search.dto;

public class SearchAggregation {

    public final String value;
    public final Long count;

    public SearchAggregation(String value, Long count) {
        this.value = value;
        this.count = count;
    }
    
}
