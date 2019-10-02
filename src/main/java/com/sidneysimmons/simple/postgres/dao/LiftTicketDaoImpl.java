package com.sidneysimmons.simple.postgres.dao;

import com.sidneysimmons.simple.postgres.configuration.DatabaseConfiguration;
import com.sidneysimmons.simple.postgres.dao.domain.TicketPurchase;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 * DAO class for lift tickets.
 * 
 * @author Sidney Simmons
 */
@Repository("liftTicketDao")
public class LiftTicketDaoImpl implements LiftTicketDao {

    @PersistenceContext(unitName = DatabaseConfiguration.PERSISTENCE_UNIT)
    private EntityManager entityManager;

    @Override
    public TicketPurchase readTicketPurchase(String confirmationId) {
        TypedQuery<TicketPurchase> query = entityManager.createQuery(
                "SELECT ticketPurchase FROM TicketPurchase ticketPurchase WHERE ticketPurchase.confirmationId = :confirmationId",
                TicketPurchase.class);
        query.setParameter("confirmationId", confirmationId);
        List<TicketPurchase> result = query.getResultList();
        return !result.isEmpty() ? result.get(0) : null;
    }

    @Override
    public TicketPurchase saveTicketPurchase(TicketPurchase ticketPurchase) {
        entityManager.persist(ticketPurchase);
        return ticketPurchase;
    }

}
