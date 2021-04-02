package me.jungwirth.playground.springboot.search.analysis;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurationContext;
import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurer;
import org.springframework.stereotype.Component;


@Component("LegalDocumentAnalysisConfigurer")
public class MyLegalDocumentAnalysisConfigurer implements LuceneAnalysisConfigurer {

    @Override
    public void configure(LuceneAnalysisConfigurationContext context) {
        context.analyzer("legal-text-german").custom()
            .tokenizer(
                StandardTokenizerFactory.class)
            .tokenFilter(
                LowerCaseFilterFactory.class)
            .tokenFilter(
                SnowballPorterFilterFactory.class)
                    .param("language", "German")
            .tokenFilter(ASCIIFoldingFilterFactory.class);   
            
    }

}

