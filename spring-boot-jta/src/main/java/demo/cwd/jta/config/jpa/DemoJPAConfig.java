package demo.cwd.jta.config.jpa;

import demo.cwd.jta.config.jpa.base.BaseJPAConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by chenweida on 2018/1/30.
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "demo.cwd.jta.dao.demo",
        entityManagerFactoryRef = "demoEntityManager",
        transactionManagerRef = "jtaTransactionManager")
public class DemoJPAConfig extends BaseJPAConfig {

    @Primary
    @Bean(name = "demoDataSource")
    public DataSource demoDataSource(Environment env) {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        Properties prop = build(env, "spring.datasource.druid.demo.");
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        ds.setUniqueResourceName("demo");
        ds.setPoolSize(5);
        ds.setXaProperties(prop);
        return ds;

    }

    @Primary
    @Bean("demoJdbcTemplate")
    public JdbcTemplate sysJdbcTemplate(@Qualifier("demoDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }


    @Primary
    @DependsOn("jtaTransactionManager")
    @Bean(name = "demoEntityManager")
    public LocalContainerEntityManagerFactoryBean customerEntityManager(
            @Qualifier("demoDataSource") DataSource dataSource
    ) throws Throwable {


        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setJtaDataSource(dataSource);
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManager.setJpaVendorAdapter(vendorAdapter);
        entityManager.setPackagesToScan("demo.cwd.jta.model.demo");
        entityManager.setPersistenceUnitName("demo");
        entityManager.setJpaPropertyMap(jpaPorperties());
        return entityManager;
    }
}
