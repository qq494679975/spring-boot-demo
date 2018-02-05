package demo.cwd.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * Created by chenweida on 2018/1/11.
 */
@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.demo.es.dao")
public class ESApplication {
    public static ApplicationContext ctx = null;

    public static void main(String[] args) {
        ctx = SpringApplication.run(ESApplication.class, args);
    }

}
