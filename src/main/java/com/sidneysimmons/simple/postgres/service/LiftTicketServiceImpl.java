package com.sidneysimmons.simple.postgres.service;

import com.sidneysimmons.simple.postgres.configuration.DatabaseConfiguration;
import com.sidneysimmons.simple.postgres.controller.domain.PurchaseLiftTicketRequest;
import com.sidneysimmons.simple.postgres.dao.LiftTicketDao;
import com.sidneysimmons.simple.postgres.dao.domain.TicketPurchase;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import javax.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Lift ticket service.
 * 
 * @author Sidney Simmons
 */
@CommonsLog
@Service("liftTicketService")
public class LiftTicketServiceImpl implements LiftTicketService {

    @Resource(name = "pricingService")
    private PricingService pricingService;

    @Resource(name = "liftTicketDao")
    private LiftTicketDao liftTicketDao;

    @Override
    public TicketPurchase getTicketPurchase(String confirmationId) {
        return liftTicketDao.readTicketPurchase(confirmationId);
    }

    @Override
    @Transactional(DatabaseConfiguration.TRANSACTION_MANAGER)
    public TicketPurchase purchaseTickets(PurchaseLiftTicketRequest request) {
        // Build the purchase
        TicketPurchase ticketPurchase = new TicketPurchase();
        ticketPurchase.setNumberOfTickets(request.getNumberOfTickets());
        ticketPurchase.setConfirmationId(UUID.randomUUID().toString());
        ticketPurchase.setDateCreated(Instant.now());

        // Set the purchase price
        BigDecimal totalCost = pricingService.calculateTotalCost(ticketPurchase.getNumberOfTickets());
        ticketPurchase.setTotalCost(totalCost);

        // Save the purchase and return it
        log.info("Saving ticket purchase " + ticketPurchase.getConfirmationId() + "...");
        return liftTicketDao.saveTicketPurchase(ticketPurchase);
    }

    @Override
    @Transactional(DatabaseConfiguration.TRANSACTION_MANAGER)
    public TicketPurchase purchaseTicketsFailure(PurchaseLiftTicketRequest request) {
        TicketPurchase ticketPurchase = purchaseTickets(request);
        throw new RuntimeException("Ticket purchase failed for " + ticketPurchase.getConfirmationId() + ". Rolling back.");
    }

}
