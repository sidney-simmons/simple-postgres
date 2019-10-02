package com.sidneysimmons.simple.postgres.dao;

import com.sidneysimmons.simple.postgres.dao.domain.TicketPurchase;

/**
 * Interface for a lift ticket DAO.
 * 
 * @author Sidney Simmons
 */
public interface LiftTicketDao {

    /**
     * Read a ticket purchase using a given confirmation ID.
     * 
     * @param confirmationId the confirmation ID
     * @return the ticket purchase if found, null otherwise
     */
    TicketPurchase readTicketPurchase(String confirmationId);

    /**
     * Save a ticket purchase.
     * 
     * @param ticketPurchase the ticket purchase to save
     * @return the saved ticket purchase
     */
    TicketPurchase saveTicketPurchase(TicketPurchase ticketPurchase);

}
