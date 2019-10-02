package com.sidneysimmons.simple.postgres.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Cache configuration.
 * 
 * @author Sidney Simmons
 */
@Configuration
@EnableCaching
public class CacheConfiguration {

    public static final String CACHE_MANAGER = "myCacheManager";
    public static final String CACHE_MANAGER_FACTORY = "myCacheManagerFactory";

    /**
     * Setup the cache manager.
     * 
     * @return the cache manager
     */
    @Bean(name = CACHE_MANAGER)
    public CacheManager myCacheManager() {
        return new EhCacheCacheManager(myCacheManagerFactory().getObject());
    }

    /**
     * Setup the cache manager factory.
     * 
     * @return the cache manager factory
     */
    @Bean(name = CACHE_MANAGER_FACTORY)
    public EhCacheManagerFactoryBean myCacheManagerFactory() {
        EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
        factory.setConfigLocation(new ClassPathResource("ehcache.xml"));
        factory.setShared(true);
        return factory;
    }

}