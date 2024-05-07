package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.BugDAO;
import fr.istic.taa.jaxrs.dao.StatusDAO;
import fr.istic.taa.jaxrs.dao.TicketDAO;
import fr.istic.taa.jaxrs.dao.UserDAO;
import fr.istic.taa.jaxrs.domain.Bug;
import fr.istic.taa.jaxrs.domain.Status;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Collections;
import java.util.List;

@Path("bug")
@Produces({"application/json", "application/xml"})
public class BugResource {
    private BugDAO BugDAO = new BugDAO();

    @POST
    @Path("/new")
    public Response newBug(String content) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(content);

            JSONObject ticketObject = (JSONObject) jsonObject.get("Ticket");

            String titreTicket = (String) ticketObject.get("titreTicket");
            String userTicket = (String) ticketObject.get("userTicket");
            String statusTicket = (String) ticketObject.get("statusTicket");
            String descriptionTicket = (String) ticketObject.get("descriptionTicket");

            UserDAO userDAO = new UserDAO();
            StatusDAO statusDAO = new StatusDAO();
            BugDAO bugDAO = new BugDAO();

            User user = userDAO.findOne(Long.parseLong(userTicket));
            Status status = statusDAO.findOne(Long.parseLong(statusTicket));
            Bug bug = new Bug(titreTicket,descriptionTicket,status,user);
            bugDAO.save(bug);
            return Response.ok().entity("SUCCESS").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to create new ticket: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/all")
    public List<Bug> getAllBugs() {
        try {
            return BugDAO.findAll();
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            // Return an empty list in case of failure
            return Collections.emptyList();
        }
    }

    @DELETE
    @Path("/all")
    public Response deleteAllBugs() {
        try {
            BugDAO.deleteAll();
            return Response.ok().entity("Bugs deleted successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete all Bugs: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{idBug}")
    public Bug getStatusById(@PathParam("idBug") Long idBug) {
        try {
            return BugDAO.findOne(idBug);
        } catch (Exception e) {
            // Return null or handle the exception as per your application's logic
            return null;
        }
    }

    @DELETE
    @Path("/{idBug}")
    public Response deleteBug(@PathParam("idBug") Long idBug) {
        try {
            Bug Bug = BugDAO.findOne(idBug);
            if (Bug == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Bug not found").build();
            }
            BugDAO.delete(Bug);
            return Response.ok().entity("Bug deleted successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete Bug: " + e.getMessage()).build();
        }
    }
}
