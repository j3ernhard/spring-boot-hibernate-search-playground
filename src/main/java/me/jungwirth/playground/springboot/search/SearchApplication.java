package me.jungwirth.playground.springboot.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * https://github.com/hibernate/hibernate-search/blob/master/integrationtest/showcase/library/src/main/java/org/hibernate/search/integrationtest/showcase/library/service/LibraryService.java
 */
@SpringBootApplication
public class SearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchApplication.class, args);
	}

}
