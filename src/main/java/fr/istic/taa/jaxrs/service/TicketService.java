package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.impl.TicketDao;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.dto.TicketDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class TicketService {
    TicketDao ticketDao = new TicketDao();

    public TicketService() {
    }
    public TicketDto getTicket(Long id){
        Ticket ticket = ticketDao.findOne(id);
        return new TicketDto(ticket.getTexte(),ticket.getAuteur().getNom(),ticket.getEtat().getLibelle(),ticket.getDateCreation());
    }
    public List<TicketDto> getTickets(){
        List<TicketDto> tickets = new ArrayList<>();
        for(Ticket ticket:ticketDao.findAll()){
            TicketDto ticketDto = new TicketDto(ticket.getTexte(),ticket.getAuteur().getNom(),ticket.getEtat().getLibelle(),ticket.getDateCreation());
            tickets.add(ticketDto);
        }
        return tickets;
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
            ticketDao.update(update);
        }
    }
}
