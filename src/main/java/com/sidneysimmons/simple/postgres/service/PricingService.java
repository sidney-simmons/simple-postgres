package com.sidneysimmons.simple.postgres.service;

import java.math.BigDecimal;

/**
 * Interface for working with ticket pricing.
 * 
 * @author Sidney Simmons
 */
public interface PricingService {

    /**
     * Calculate the total cost for a given number of tickets.
     * 
     * @param numberOfTickets the number of tickets being purchased
     * @return the total cost
     */
    BigDecimal calculateTotalCost(Integer numberOfTickets);

}
