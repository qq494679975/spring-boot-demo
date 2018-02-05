package demo.cwd.jta.config.jpa;

import demo.cwd.jta.config.jpa.base.BaseJPAConfig;
import demo.cwd.jta.config.jta.AtomikosJtaPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by chenweida on 2018/1/30.
 */

@Configuration
@EnableJpaRepositories(
        basePackages = "demo.cwd.jta.dao.test",
        entityManagerFactoryRef = "testEntityManager",
        transactionManagerRef = "jtaTransactionManager")
public class TestJPAConfig extends BaseJPAConfig {

    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;


    @Autowired
    @Bean(name = "testSource")
    public AtomikosDataSourceBean businessDataSource(Environment env) {

        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        Properties prop = build(env, "spring.datasource.druid.test.");
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        ds.setUniqueResourceName("test");
        ds.setPoolSize(5);
        ds.setXaProperties(prop);

        return ds;
    }


    @Bean("testJdbcTemplate")
    public JdbcTemplate busJdbcTemplate(
            @Qualifier("testSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @DependsOn("jtaTransactionManager")
    @Bean(name = "testEntityManager")
    public LocalContainerEntityManagerFactoryBean customerEntityManager(
            @Qualifier("testSource") DataSource dataSource
    ) throws Throwable {

        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setJtaDataSource(dataSource);

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManager.setJpaVendorAdapter(vendorAdapter);

        entityManager.setPackagesToScan("demo.cwd.jta.model.test");
        entityManager.setPersistenceUnitName("test");
        entityManager.setJpaPropertyMap(jpaPorperties());
        return entityManager;
    }
}
