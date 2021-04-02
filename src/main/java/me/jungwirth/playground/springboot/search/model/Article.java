package me.jungwirth.playground.springboot.search.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;


@Entity
@Indexed
public class Article extends AbstractEntity {

    @GenericField(name = "number", sortable = Sortable.YES)
    private String number;

    private String eli;


    @FullTextField(name = "title", analyzer = "legal-text-german")
    @GenericField(name = "title_sort", sortable = Sortable.YES)
    private String title;
    
    @FullTextField(name = "text", analyzer = "legal-text-german")
    @Column(length = 4096)
    private String text; 


    /**
     * https://docs.jboss.org/hibernate/search/6.0/migration/html_single/#calendarbridge
     */
    @GenericField
    @Column(columnDefinition = "DATE")
    private LocalDate effectiveDate;

    @GenericField
    @Column(columnDefinition = "DATE", nullable = true)
    private LocalDate expireDate;

    @IndexedEmbedded
    @ManyToOne
    @JoinColumn(name = "document_id", nullable = false)
    @JsonManagedReference
    private LegalDocument document;

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setEli(String eli) {
        this.eli = eli;
    }

    public String getEli() {
        return eli;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public LegalDocument getDocument() {
        return document;
    }

    public void setDocument(LegalDocument document) {
        this.document = document;
    }

}

