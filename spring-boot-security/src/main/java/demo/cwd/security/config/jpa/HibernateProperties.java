package demo.cwd.security.config.jpa;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by chenweida on 2017/4/6.
 */
@Component
public class HibernateProperties {
    @Value("${hibernate.dialect}")
    private String dialect;
    @Value("${hibernate.show_sql}")
    private String show_sql;
    @Value("${hibernate.ejb.naming_strategy}")
    private String naming_strategy;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddl_auto;
    @Value("${hibernate.format_sql}")
    private String format_sql;

    public  Properties hibProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect",dialect);
        properties.put("hibernate.show_sql", show_sql);
        properties.put("hibernate.ejb.naming_strategy", naming_strategy);
        properties.put("hibernate.hbm2ddl.auto",hbm2ddl_auto);
        properties.put("hibernate.format_sql",format_sql);
        return properties;
    }
}
