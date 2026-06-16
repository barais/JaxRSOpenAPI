package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Ticket;
import java.util.List;

public class TicketDao extends  AbstractJpaDao<Long, Ticket>{

    public TicketDao() {
        super(Ticket.class);
    }

    public List<Ticket> findByEmail(String email) {
        return entityManager.createQuery(
                        "SELECT t FROM Ticket t WHERE buyerEmail = :email",
                        Ticket.class)
                .setParameter("email", email)
                .getResultList();
    }

    public Long countTickets() {
        Long count =  entityManager.createQuery(
                        "SELECT COUNT(t) FROM Ticket t",
                        Long.class)
                .getSingleResult();
        entityManager.clear();
        return count;
    }

}
