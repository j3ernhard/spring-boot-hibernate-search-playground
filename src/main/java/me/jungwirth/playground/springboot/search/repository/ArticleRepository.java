package me.jungwirth.playground.springboot.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.jungwirth.playground.springboot.search.model.Article;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    
}
