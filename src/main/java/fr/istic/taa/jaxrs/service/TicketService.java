package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.impl.TicketDao;
import fr.istic.taa.jaxrs.domain.Ticket;

import java.util.List;


public class TicketService {
    private final TicketDao ticketDao;

    public TicketService() {
        this.ticketDao=new TicketDao();
    }
    public Ticket getTicket(Long id){
        return ticketDao.findOne(id);
    }
    public List<Ticket> getTickets(){
        return ticketDao.findAll();
    }
    public void addTicket(Ticket ticket){
        ticketDao.save(ticket);
    }
    public void removeTicket(Long id){
        ticketDao.deleteById(id);
    }

    public void updateTicket(Long id, Ticket ticket){
        if(ticketDao.findOne(id)==null){
            throw new RuntimeException("Aucun ticket trouvé");
        }
        else{
            Ticket update = ticketDao.findOne(id);
            update.setEtat(ticket.getEtat());
            update.setTexte(ticket.getTexte());
        }
    }
}
