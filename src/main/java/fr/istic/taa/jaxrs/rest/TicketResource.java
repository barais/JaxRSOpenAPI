package fr.istic.taa.jaxrs.rest;


import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.service.TicketService;


import javax.ws.rs.*;
import java.util.List;

@Path("/tickets")
@Produces({"application/json"})
public class TicketResource {
    TicketService service = new TicketService();

    public TicketResource() {
    }
    @GET
    @Path("/{id}")
    public Ticket getTicket(@PathParam("id") Long id){
        return service.getTicket(id);
    }
    @GET
    @Path("/")
    public List<Ticket> getList(){
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
