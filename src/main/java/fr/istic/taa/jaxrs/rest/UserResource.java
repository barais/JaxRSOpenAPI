package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.UserDAO;
import fr.istic.taa.jaxrs.domain.Pet;
import fr.istic.taa.jaxrs.domain.User;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("user")
@Produces({"application/json", "application/xml"})
public class UserResource {
    private UserDAO userDAO = new UserDAO();

    @POST
    @Consumes("application/json")
    public Response addPet(
            @Parameter(description = "Pet object that needs to be added to the store", required = true) Pet pet) {
        return Response.ok().entity("SUCCESS").build();
    }

    @POST
    @Path("/new")
    public Response newUser() {
        User user = new User();
        userDAO.save(user);
        return Response.ok().entity("SUCCESS").build();
    }

    @GET
    @Path("/all")
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    @DELETE
    @Path("/all")
    public Response deleteAllUsers() {
        userDAO.deleteAll();
        return Response.ok().entity("User deleted successfully").build();
    }

    @GET
    @Path("/{idUser}")
    public User getUserById(@PathParam("idUser") Long idUser) {
        return userDAO.findOne(idUser);
    }

    @DELETE
    @Path("/{idUser}")
    public Response deleteUser(@PathParam("idUser") Long idUser) {
        User user = userDAO.findOne(idUser);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }
        userDAO.delete(user);
        return Response.ok().entity("User deleted successfully").build();
    }

}