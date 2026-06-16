package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.generic.ConcertDao;
import fr.istic.taa.jaxrs.dao.generic.TicketDao;
import fr.istic.taa.jaxrs.dao.generic.UserDao;
import fr.istic.taa.jaxrs.domain.Concert;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.TicketCreateDto;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import java.util.Date;
import java.util.List;

public class TicketService {
    private final TicketDao ticketDao = new TicketDao();
    private final ConcertDao concertDao = new ConcertDao();
    private final UserDao userDao = new UserDao();

    public Long countTickets(){
        return ticketDao.countTickets();
    }
    public List<Ticket> getAllTickets() {
        return ticketDao.findAll();
    }

    public List<Ticket> findByEmail(String email) {
        return ticketDao.findByEmail(email);
    }

    public Ticket getTicket(long id) {
        return ticketDao.findOne(id);
    }

    public void deleteTicket(long id) {
        ticketDao.deleteById(id);
    }

    // Méthode métier — acheter un ticket
    // Méthode métier — acheter un ticket
    public Ticket buyTicket(TicketCreateDto dto) {
        Concert concert = concertDao.findOne(dto.getConcertId());
        if (concert == null) throw new NotFoundException("Concert non trouvé");
        if (concert.isIsCanceled()) throw new BadRequestException("Concert annulé");
        if (concert.getAvailableTickets() <= 0) throw new BadRequestException("Complet");

        Ticket ticket = new Ticket();
        ticket.setPrice(concert.getPrice());
        ticket.setDate(new Date());
        ticket.setBuyerEmail(dto.getBuyerEmail());
        ticket.setQuantity(dto.getQuantity());
        ticket.setConcert(concert);

        // Initialisation des nouveaux états
        ticket.setStatus("confirmed"); // État vert dans ton Angular
        ticket.setCanceled(false);
        ticket.setRefunded(false);

        concert.setAvailableTickets(concert.getAvailableTickets() - dto.getQuantity());
        concertDao.update(concert);
        ticketDao.save(ticket);
        return ticket;
    }

    public Ticket cancelTicket(long id) {
        Ticket ticket = getTicket(id);
        if (ticket == null) throw new NotFoundException("Ticket non trouvé");

        ticket.setCanceled(true);
        ticket.setStatus("cancelled"); // État rouge dans ton Angular
        ticket.setCancelDate(new Date());
        ticketDao.update(ticket);
        return ticket;
    }

    public Ticket transferTicket(long ticketId, String newOwnerEmail) {
        Ticket ticket = getTicket(ticketId);

        if (ticket == null) throw new NotFoundException("Ticket non trouvé");
        if (ticket.isCanceled() || "cancelled".equals(ticket.getStatus())) {
            throw new BadRequestException("Ticket annulé, transfert impossible");
        }

        // --- Logique d'historique de transfert ---
        // On sauvegarde qui donne le ticket (l'actuel buyerEmail)
        ticket.setTransferorEmail(ticket.getBuyerEmail());

        // On met à jour le nouveau propriétaire
        ticket.setBuyerEmail(newOwnerEmail);

        // On change le statut pour déclencher la couleur violette
        ticket.setStatus("transferred");

        ticketDao.update(ticket);
        return ticket;
    }
    // Méthode métier — rembourser un ticket
    public Ticket refundTicket(long id) {
        Ticket ticket = getTicket(id);
        ticket.setRefunded(true);
        ticket.setRefundDate(new Date());
        ticketDao.update(ticket);
        return ticket;
    }

}