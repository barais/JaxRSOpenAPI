package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.impl.TicketDao;
import fr.istic.taa.jaxrs.domain.Tag;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.TicketDto;
import fr.istic.taa.jaxrs.service.TicketService;
import io.swagger.v3.oas.annotations.Parameter;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Path("/tickets")
@Produces({"application/json"})
public class TicketResource {
    TicketService service = new TicketService();

    public TicketResource() {
    }
    @GET
    @Path("/{id}")
    public TicketDto getTicket(@PathParam("id") Long id){
        return service.getTicket(id);
    }
    @GET
    @Path("/")
    public List<TicketDto> getList(){
        return service.getTickets();
    }

    @POST
    @Consumes("application/json")
    public void createTicket(Ticket ticket){
        service.addTicket(ticket);
    }

    @DELETE
    @Path("/{id}")
    public void deleteTicket(@PathParam("id")Long id){
        service.removeTicket(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    public void updateTicket(@PathParam("id")Long id, Ticket ticket){
        service.updateTicket(id,ticket);
    }
}
