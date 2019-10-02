package com.sidneysimmons.simple.postgres.controller.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * Get all cache stats response.
 * 
 * @author Sidney Simmons
 */
@Data
public class GetAllCacheStatsResponse {

    private List<CacheStats> cacheStats = new ArrayList<>();

}
