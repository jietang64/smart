package com.jietang.smart.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import java.beans.PropertyVetoException;

/**
 * 数据源配置
 */
@Component
public class DbConfig {

    @Value("${jdbc.jdbcUrl}")
    private String jdbcUrl;
    @Value("${jdbc.user}")
    private String user;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.initialPoolSize}")
    private int initialPoolSize;
    @Value("${jdbc.maxIdleTime}")
    private int maxIdleTime;
    @Value("${jdbc.maxPoolSize}")
    private int maxPoolSize;
    @Value("${jdbc.minPoolSize}")
    private int minPoolSize;
    @Value("${jdbc.checkoutTimeout}")
    private int checkoutTimeout;
    @Value("${jdbc.acquireIncrement}")
    private int acquireIncrement;
    @Value("${jdbc.acquireRetryAttempts}")
    private int acquireRetryAttempts;
    @Value("${jdbc.acquireRetryDelay}")
    private int acquireRetryDelay;
    @Value("${jdbc.idleConnectionTestPeriod}")
    private int idleConnectionTestPeriod;

    @Bean
    @Primary
    public ComboPooledDataSource getDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setInitialPoolSize(initialPoolSize);
        dataSource.setMaxIdleTime(maxIdleTime);
        dataSource.setMaxPoolSize(maxPoolSize);
        dataSource.setMinPoolSize(minPoolSize);
        dataSource.setCheckoutTimeout(checkoutTimeout);
        dataSource.setAcquireIncrement(acquireIncrement);
        dataSource.setAcquireRetryAttempts(acquireRetryAttempts);
        dataSource.setAcquireRetryDelay(acquireRetryDelay);
        dataSource.setIdleConnectionTestPeriod(idleConnectionTestPeriod);
        return dataSource;
    }

    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory getSessionFactory(ComboPooledDataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setConfigLocation(resolver.getResource("classpath:/mapper/common/Configuration.xml"));
        factoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/*/*Mapper.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "trans")
    @Primary
    public DataSourceTransactionManager getTransactionManager(ComboPooledDataSource dataSource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

}
