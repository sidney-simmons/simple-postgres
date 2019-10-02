package com.sidneysimmons.simple.postgres.controller.domain;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.Data;

/**
 * Purchase lift ticket response object.
 * 
 * @author Sidney Simmons
 */
@Data
public class PurchaseLiftTicketResponse {

    private Integer numberOfTickets;

    private BigDecimal totalCost;

    private String confirmationId;

    private Instant purchaseTime;

}
