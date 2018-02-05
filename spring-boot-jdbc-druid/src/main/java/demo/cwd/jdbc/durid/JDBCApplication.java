package demo.cwd.jdbc.durid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by chenweida on 2018/1/12.
 * 路径 http://localhost:8080/druid/index.html
 */
@SpringBootApplication
public class JDBCApplication {
    public static ApplicationContext ctx = null;

    public static void main(String[] args) {
        ctx = SpringApplication.run(JDBCApplication.class, args);
    }

}
