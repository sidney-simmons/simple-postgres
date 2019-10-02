package com.sidneysimmons.simple.postgres.controller;

import com.sidneysimmons.simple.postgres.controller.domain.GetPurchaseDetailsResponse;
import com.sidneysimmons.simple.postgres.controller.domain.PurchaseLiftTicketRequest;
import com.sidneysimmons.simple.postgres.controller.domain.PurchaseLiftTicketResponse;
import com.sidneysimmons.simple.postgres.dao.domain.TicketPurchase;
import com.sidneysimmons.simple.postgres.service.LiftTicketService;
import javax.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Lift ticket controller for lift ticket related endpoints.
 * 
 * @author Sidney Simmons
 */
@RestController
@RequestMapping(value = "/lift-ticket", produces = MediaType.APPLICATION_JSON_VALUE)
public class LiftTicketController {

    @Resource(name = "liftTicketService")
    private LiftTicketService liftTicketService;

    /**
     * Purchase a new lift ticket.
     * 
     * @param request the request
     * @return the purchased lift ticket response
     */
    @PostMapping(value = "/purchase-ticket")
    public PurchaseLiftTicketResponse purchaseLiftTicket(@RequestBody PurchaseLiftTicketRequest request) {
        // Purchase the ticket
        TicketPurchase ticketPurchase = liftTicketService.purchaseTickets(request);

        // Return the purchase details
        PurchaseLiftTicketResponse response = new PurchaseLiftTicketResponse();
        response.setNumberOfTickets(ticketPurchase.getNumberOfTickets());
        response.setTotalCost(ticketPurchase.getTotalCost());
        response.setConfirmationId(ticketPurchase.getConfirmationId());
        response.setPurchaseTime(ticketPurchase.getDateCreated());
        return response;
    }

    /**
     * Purchase a new lift ticket and then cause a roll back.
     * 
     * @param request the request
     * @return nothing because an exception will be thrown
     */
    @PostMapping(value = "/purchase-ticket-failure")
    public PurchaseLiftTicketResponse purchaseLiftTicketFailure(@RequestBody PurchaseLiftTicketRequest request) {
        // Purchase the ticket and allow it to be rolled back
        liftTicketService.purchaseTicketsFailure(request);

        // We won't get to this point anyway
        return null;
    }

    /**
     * Get the purchase details for a given confirmation ID.
     * 
     * @param confirmationId the confirmation ID
     * @return the purchase details
     */
    @GetMapping(value = "/get-purchase-details")
    public ResponseEntity<GetPurchaseDetailsResponse> getPurchaseDetails(@RequestParam String confirmationId) {
        // Get the purchase and return the details
        TicketPurchase ticketPurchase = liftTicketService.getTicketPurchase(confirmationId);
        if (ticketPurchase != null) {
            GetPurchaseDetailsResponse response = new GetPurchaseDetailsResponse();
            response.setNumberOfTickets(ticketPurchase.getNumberOfTickets());
            response.setTotalCost(ticketPurchase.getTotalCost());
            response.setConfirmationId(ticketPurchase.getConfirmationId());
            response.setPurchaseTime(ticketPurchase.getDateCreated());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
