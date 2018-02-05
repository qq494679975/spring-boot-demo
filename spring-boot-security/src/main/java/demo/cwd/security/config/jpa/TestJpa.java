package demo.cwd.security.config.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by zhangdan on 2017/12/26.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "testEntityManagerFactory",
        transactionManagerRef = "testTransactionManager",
        basePackages = {"demo.cwd.security.dao"})  //设置Repository所在位置
public class TestJpa {
    @Autowired
    private HibernateProperties hibernateProperties;

    @Bean(name = "testDataSource")
    @ConfigurationProperties(prefix="spring.datasource.test")
    public DataSource jkeduDataSource() {
        return DataSourceBuilder.create().build();
    }



    @Bean(name = "testEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary(
            @Qualifier("testDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource);
        emfb.setPackagesToScan("demo.cwd.security.model");
        emfb.setPersistenceUnitName("jkedu");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emfb.setJpaVendorAdapter(vendorAdapter);
        Properties properties = new Properties();
        properties.putAll(hibernateProperties.hibProperties());
        properties.put("hibernate.ejb.naming_strategy","org.hibernate.cfg.DefaultNamingStrategy");
        emfb.setJpaProperties(properties);

        return emfb;
    }


    @Bean(name = "testTransactionManager")
    JpaTransactionManager transactionManagerSecondary(
            @Qualifier("testEntityManagerFactory")EntityManagerFactory builder) {
        return new JpaTransactionManager(builder);
    }
}