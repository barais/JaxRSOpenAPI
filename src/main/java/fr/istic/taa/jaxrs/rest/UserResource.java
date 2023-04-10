package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.service.UserService;

import javax.ws.rs.*;
import java.util.List;

@Path("/users")
@Produces({"application/json"})
public class UserResource {
    UserService service = new UserService();

    public UserResource() {
    }
    @GET
    @Path("/{id}")
    public User getUser(@PathParam("id") Long id){
        return service.getUser(id);
    }
    @GET
    @Path("/")
    public List<User> getList(){
        return service.getUsers();
    }

    @POST
    @Consumes("application/json")
    public void createUser(User user){
        service.addUser(user);
    }

    @DELETE
    @Path("/{id}")
    public void deleteUser(@PathParam("id")Long id){
        service.removeUser(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    public void updateUser(@PathParam("id")Long id, User user){
        service.updateUser(id,user);
    }
}