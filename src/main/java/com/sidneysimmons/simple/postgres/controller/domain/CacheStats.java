package com.sidneysimmons.simple.postgres.controller.domain;

import lombok.Data;

/**
 * Cache stats object for a specific cache region.
 * 
 * @author Sidney Simmons
 */
@Data
public class CacheStats {

    private String region;

    private Integer size;

    private Long hitCount;

    private Long missCount;

}
