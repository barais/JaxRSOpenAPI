package fr.istic.taa.jaxrs.rest;


import fr.istic.taa.jaxrs.domain.Artist;
import fr.istic.taa.jaxrs.dto.PersonCreateDto;
import fr.istic.taa.jaxrs.dto.PersonUpdateDto;
import fr.istic.taa.jaxrs.service.ArtistService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("artists")
@Produces(MediaType.APPLICATION_JSON)
public class ArtistResource {
    private final ArtistService artistService = new ArtistService();

    @GET
    @Path("/")
    public List<Artist> getAllArtists(){
        return artistService.getAllArtists();
    }

    @GET
    @Path("/{id}")
    public Artist getArtist(@PathParam("id") long id){return artistService.getArtist(id);}

    @POST
    @Path("/")
    public Artist createArtist(PersonCreateDto createArtist){
        return artistService.createArtist(createArtist);
    }

    @PUT
    @Path("/{id}")
    public Artist updateArtist(@PathParam("id") long id, PersonUpdateDto personUpdateDto){

        return artistService.updateArtist(id, personUpdateDto);
    }

    @DELETE
    @Path("/{id}")
    public void deleteArtist(@PathParam("id") long id){artistService.deleteArtist(id);}

}
