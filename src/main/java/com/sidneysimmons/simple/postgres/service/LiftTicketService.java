package com.sidneysimmons.simple.postgres.service;

import com.sidneysimmons.simple.postgres.controller.domain.PurchaseLiftTicketRequest;
import com.sidneysimmons.simple.postgres.dao.domain.TicketPurchase;

/**
 * Interface for lift ticket services.
 * 
 * @author Sidney Simmons
 */
public interface LiftTicketService {

    /**
     * Get a specific ticket puchase using the given confirmation ID.
     * 
     * @param confirmationId the confirmation ID
     * @return the ticket purchase if found, null otherwise
     */
    TicketPurchase getTicketPurchase(String confirmationId);

    /**
     * Purchase lift tickets using the provided purchase request.
     * 
     * @param request the purchase request
     * @return the completed purchase
     */
    TicketPurchase purchaseTickets(PurchaseLiftTicketRequest request);

    /**
     * Purchase lift tickets and then throw a runtime exception to cause a roll back.
     * 
     * @param request the purchase request
     * @return nothing because an exception will be thrown
     */
    TicketPurchase purchaseTicketsFailure(PurchaseLiftTicketRequest request);

}
