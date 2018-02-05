package demo.cwd.data.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

/**
 * Created by chenweida on 2018/1/25.
 */

@SpringBootApplication
@EnableSpringDataWebSupport
public class DataRestApplication {
    public static ApplicationContext ctx = null;

    public static void main(String[] args) {
        ctx = SpringApplication.run(DataRestApplication.class, args);
    }

}
