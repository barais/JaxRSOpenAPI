package fr.istic.taa.jaxrs.rest;


import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.PersonCreateDto;
import fr.istic.taa.jaxrs.dto.PersonUpdateDto;
import fr.istic.taa.jaxrs.service.UserService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserService userService = new UserService();

    @GET
    @Path("/")

    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GET
    @Path("/{id}")
    public User getUser(@PathParam("id") long id){return userService.getUser(id);}

    @POST
    @Path("/")
    public User createUser(PersonCreateDto createUser){
        return userService.createUser(createUser);
    }

    @PUT
    @Path("/{id}")
    public User updateUser(@PathParam("id") long id, PersonUpdateDto personUpdateDto){

        return userService.updateUser(id, personUpdateDto);
    }

    @DELETE
    @Path("/{id}")
    public void deleteUser(@PathParam("id") long id){userService.deleteUser(id);}

}
