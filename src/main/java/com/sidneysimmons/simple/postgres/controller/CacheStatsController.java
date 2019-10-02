package com.sidneysimmons.simple.postgres.controller;

import com.sidneysimmons.simple.postgres.configuration.CacheConfiguration;
import com.sidneysimmons.simple.postgres.controller.domain.CacheStats;
import com.sidneysimmons.simple.postgres.controller.domain.GetAllCacheStatsResponse;
import javax.annotation.Resource;
import net.sf.ehcache.Cache;
import net.sf.ehcache.statistics.StatisticsGateway;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Cache stats controller for working with cache statistics.
 * 
 * @author Sidney Simmons
 */
@RestController
@RequestMapping(value = "/cache-stats", produces = MediaType.APPLICATION_JSON_VALUE)
public class CacheStatsController {

    @Resource(name = CacheConfiguration.CACHE_MANAGER)
    private CacheManager cacheManager;

    /**
     * Get cache stats for all cache regions.
     * 
     * @return stats for all cache regions
     */
    @GetMapping(value = "/get-all-stats")
    public GetAllCacheStatsResponse getAllStats() {
        GetAllCacheStatsResponse response = new GetAllCacheStatsResponse();
        for (String cacheName : cacheManager.getCacheNames()) {
            Cache cache = (Cache) cacheManager.getCache(cacheName).getNativeCache();
            StatisticsGateway statisticsGateway = cache.getStatistics();

            CacheStats stats = new CacheStats();
            stats.setRegion(cache.getName());
            stats.setSize(cache.getSize());
            stats.setHitCount(statisticsGateway.cacheHitCount());
            stats.setMissCount(statisticsGateway.cacheMissCount());

            response.getCacheStats().add(stats);
        }
        return response;
    }

}
