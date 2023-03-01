package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.service.TicketService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/ticket")
@Produces({"application/json", "application/xml"})
public class TicketResource {
    private final TicketService service;

    public TicketResource() {
        this.service = new TicketService();
    }
    @GET
    @Path("/{id}")
    private Ticket getTicket(@PathParam("id") Long id){
        return service.getTicket(id);
    }
}
