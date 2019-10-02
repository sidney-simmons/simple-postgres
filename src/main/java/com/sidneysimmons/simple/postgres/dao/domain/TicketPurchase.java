package com.sidneysimmons.simple.postgres.dao.domain;

import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * Ticket purchase.
 * 
 * @author Sidney Simmons
 */
@Data
@Entity
@Table(name = "ticket_purchase")
public class TicketPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_purchase_id")
    private Long id;

    @Column(name = "number_of_tickets")
    private Integer numberOfTickets;

    @Column(name = "total_cost")
    private BigDecimal totalCost;

    @Column(name = "confirmation_id")
    private String confirmationId;

    @Column(name = "date_created")
    private Instant dateCreated;

}
