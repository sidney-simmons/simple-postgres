package com.sidneysimmons.simple.postgres.controller.domain;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.Data;

/**
 * Get lift ticket purchase details object.
 * 
 * @author Sidney Simmons
 */
@Data
public class GetPurchaseDetailsResponse {

    private Integer numberOfTickets;

    private BigDecimal totalCost;

    private String confirmationId;

    private Instant purchaseTime;

}
