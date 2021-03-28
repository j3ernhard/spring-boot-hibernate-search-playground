package me.jungwirth.playground.springboot.search.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.search.engine.backend.types.Aggregable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

/**
 * 
 */
@Entity
@Indexed
public class LegalDocument extends AbstractEntity {


    @FullTextField
    @Column(length = 1024)
    private String title;
    
    
    @KeywordField(name = "titleShort")
    private String titleShort;

    @KeywordField(aggregable = Aggregable.YES)
    private String abbreviation;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Article> articles;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleShort() {
        return titleShort;
    }

    public void setTitleShort(String titleShort) {
        this.titleShort = titleShort;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}