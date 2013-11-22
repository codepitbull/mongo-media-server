package de.codepitbull.mongodb.spring;

import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Jochen Mader
 */
@Configuration
@ComponentScan(basePackages = "de.codepitbull.mongodb", excludeFilters = {@ComponentScan.Filter(Configuration.class)})
@EnableMongoRepositories("de.codepitbull.mongodb.repository")
@EnableWebMvc
public class MainConfiguration extends AbstractMongoConfiguration {
    @Override
    protected String getDatabaseName() {
        return "test";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new Mongo("localhost");
    }

    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
    }
}
