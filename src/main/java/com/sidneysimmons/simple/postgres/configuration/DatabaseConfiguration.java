package com.sidneysimmons.simple.postgres.configuration;

import java.util.HashMap;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Database configuration.
 * 
 * @author Sidney Simmons
 */
@Configuration
public class DatabaseConfiguration {

    @Autowired
    private Environment environment;

    public static final String DATASOURCE = "myDataSource";
    public static final String ENTITY_MANAGER_FACTORY = "myEntityManagerFactory";
    public static final String PERSISTENCE_UNIT = "myPersistenceUnit";
    public static final String TRANSACTION_MANAGER = "myTransactionManager";

    /**
     * Setup the data source.
     * 
     * @return the data source
     */
    @Bean(name = DATASOURCE)
    public DataSource myDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("mydatasource.driver"));
        dataSource.setUrl(environment.getProperty("mydatasource.url"));
        dataSource.setUsername(environment.getProperty("mydatasource.username"));
        dataSource.setPassword(environment.getProperty("mydatasource.password"));
        return dataSource;
    }

    /**
     * Setup the entity manager factory.
     * 
     * @return the entity manager factory
     */
    @Bean(name = ENTITY_MANAGER_FACTORY)
    public EntityManagerFactory myEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPersistenceUnitName(PERSISTENCE_UNIT);
        em.setPackagesToScan("com.sidneysimmons.simple.postgres");
        em.setDataSource(myDataSource());
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("mydatasource.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", environment.getProperty("mydatasource.hibernate.dialect"));
        em.setJpaPropertyMap(properties);
        em.afterPropertiesSet();
        return em.getObject();
    }

    /**
     * Setup the transaction manager.
     * 
     * @return the transaction manager
     */
    @Bean(name = TRANSACTION_MANAGER)
    public PlatformTransactionManager myTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(myEntityManagerFactory());
        return transactionManager;
    }

}
