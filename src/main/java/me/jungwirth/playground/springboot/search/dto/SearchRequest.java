package me.jungwirth.playground.springboot.search.dto;

import java.time.LocalDate;

import org.springframework.data.domain.Pageable;

public class SearchRequest {

    public final String keyword;

    public final String abbreviation;

    public final LocalDate date;

    public final Pageable page;


    public SearchRequest(String keyword, String abbreviation, LocalDate date, Pageable page) {
        this.keyword = keyword;
        this.abbreviation = abbreviation;
        this.date = date;
        this.page = page;
    }

}
