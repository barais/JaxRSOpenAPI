package fr.istic.taa.jaxrs.rest;


import fr.istic.taa.jaxrs.domain.Organizer;
import fr.istic.taa.jaxrs.dto.PersonCreateDto;
import fr.istic.taa.jaxrs.dto.PersonUpdateDto;
import fr.istic.taa.jaxrs.service.OrganizerService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("organizers")
@Produces(MediaType.APPLICATION_JSON)
public class OrganizerResource {
    private final OrganizerService organizerService = new OrganizerService();

    @GET
    @Path("/")
    public List<Organizer> getAllOrganizers(){
        return organizerService.getAllOrganizers();
    }

    @GET
    @Path("/{id}")
    public Organizer getOrganizer(@PathParam("id") long id){return organizerService.getOrganizer(id);}

    @POST
    @Path("/")
    public Organizer createOrganizer(PersonCreateDto createOrganizer){
        return organizerService.createOrganizer(createOrganizer);
    }

    @PUT
    @Path("/{id}")
    public Organizer updateOrganizer(@PathParam("id") long id, PersonUpdateDto personUpdateDto){

        return organizerService.updateOrganizer(id, personUpdateDto);
    }

    @DELETE
    @Path("/{id}")
    public void deleteOrganizer(@PathParam("id") long id){organizerService.deleteOrganizer(id);}

}
