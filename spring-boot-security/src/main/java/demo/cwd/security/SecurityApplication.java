package demo.cwd.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by chenweida on 2018/1/24.
 */
@SpringBootApplication
public class SecurityApplication {
    public static ApplicationContext ctx = null;

    public static void main(String[] args) {
        ctx = SpringApplication.run(SecurityApplication.class, args);
    }

}