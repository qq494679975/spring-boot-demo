package demo.cwd.jdbc.durid.config.druid;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Administrator on 2016.10.20.
 * 多数据源配置
 */
@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;
    @Value("${spring.datasource.initialSize}")
    private Integer initialSize;
    @Value("${spring.datasource.minIdle}")
    private Integer minIdle;
    @Value("${spring.datasource.maxActive}")
    private Integer maxActive;
    @Value("${spring.datasource.maxWait}")
    private Integer maxWait;
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private Integer timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private Integer minEvictableIdleTimeMillis;
    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;
    @Value("${spring.datasource.testWhileIdle}")
    private Boolean testWhileIdle;
    @Value("${spring.datasource.testOnBorrow}")
    private Boolean testOnBorrow;
    @Value("${spring.datasource.testOnReturn}")
    private Boolean testOnReturn;
    @Value("${spring.datasource.poolPreparedStatements}")
    private Boolean poolPreparedStatements;
    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private Integer maxPoolPreparedStatementPerConnectionSize;
    @Value("${spring.datasource.removeAbandoned}")
    private Boolean removeAbandoned;
    @Value("${spring.datasource.removeAbandonedTimeout}")
    private Integer removeAbandonedTimeout;
    @Value("${spring.datasource.logAbandoned}")
    private Boolean logAbandoned;
    @Value("${spring.datasource.filters")
    private String filters;


    @Value("${spring.datasource.url}")
    private String primaryReadWriteUrl;
    @Value("${spring.datasource.username}")
    private String primaryReadWriteUsername;
    @Value("${spring.datasource.password}")
    private String primaryReadWritePassword;

    /**
     * 主数据源
     *
     * @return
     */
    @Bean(name = "testDataSource")
    @Primary//主库 默认不写名字用这个
    public DataSource testDataSource(StatFilter statFilter) throws SQLException {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(primaryReadWriteUrl);
        datasource.setUsername(primaryReadWriteUsername);
        datasource.setPassword(primaryReadWritePassword);
        datasource.setDriverClassName(driverClassName);

        //configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        datasource.setRemoveAbandoned(removeAbandoned);
        datasource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
        datasource.setLogAbandoned(logAbandoned);
        datasource.setFilters(filters);
        datasource.setConnectProperties(properties());//;# 通过connectProperties属性来打开mergeSql功能；慢SQL记录
        datasource.setUseGlobalDataSourceStat(true);// 合并多个DruidDataSource的监控数据

        List proxyFilters = new ArrayList<>();
        proxyFilters.add(statFilter);
        datasource.setProxyFilters(proxyFilters);
        return datasource;
    }

    private Properties properties() {
        Properties properties = new Properties();
        properties.put("druid.stat.mergeSql", "true");
        properties.put("slowSqlMillis", "1000");
        return properties;
    }

    //------------------------------------druid 监控----------------------------------------------
    @Bean
    public ServletRegistrationBean statViewServlet() {
        //创建servlet注册实体
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //设置ip白名单
        //servletRegistrationBean.addInitParameter("allow","127.0.0.1");
        //设置ip黑名单，如果allow与deny共同存在时,deny优先于allow
        // servletRegistrationBean.addInitParameter("deny","192.168.0.19");
        //设置控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", "jkzl");
        servletRegistrationBean.addInitParameter("loginPassword", "jkzlehr");
        //是否可以重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(WebStatFilter webStatFilter) {
        //创建过滤器
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(webStatFilter);
        //设置过滤器过滤路径
        filterRegistrationBean.addUrlPatterns("/*");
        //忽略过滤的形式
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    @Bean
    public StatFilter statFilter() {
        return new StatFilter();
    }

    @Bean
    public WebStatFilter webStatFilter() {
        return new WebStatFilter();
    }
    //------------------------------------druid 监控----------------------------------------------
}
