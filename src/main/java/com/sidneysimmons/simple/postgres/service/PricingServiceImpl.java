package com.sidneysimmons.simple.postgres.service;

import java.math.BigDecimal;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Default implementation of the {@link PricingService} interface.
 * 
 * @author Sidney Simmons
 */
@CommonsLog
@Service("pricingService")
public class PricingServiceImpl implements PricingService {

    /**
     * Calculate the total cost for a given number of tickets.
     * 
     * @param numberOfTickets the number of tickets being purchased
     * @return the total cost
     */
    @Override
    @Cacheable(value = "totalCostOfTickets", key = "#numberOfTickets")
    public BigDecimal calculateTotalCost(Integer numberOfTickets) {
        // Simulate a long running operation to help illustrate the caching mechanism
        try {
            Thread.sleep(4000);
        } catch (Exception e) {
            log.error("Cannot sleep the thread.", e);
        }

        // Calculate and return the total cost
        BigDecimal basePrice = new BigDecimal("5.12");
        if (numberOfTickets >= 4) {
            basePrice = basePrice.subtract(new BigDecimal("0.44"));
        }
        return basePrice.multiply(new BigDecimal(numberOfTickets));
    }

}
