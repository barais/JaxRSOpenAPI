package fr.istic.taa.jaxrs.rest;


import fr.istic.taa.jaxrs.domain.Admin;
import fr.istic.taa.jaxrs.dto.PersonCreateDto;
import fr.istic.taa.jaxrs.dto.PersonUpdateDto;
import fr.istic.taa.jaxrs.service.AdminService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("admins")
@Produces(MediaType.APPLICATION_JSON)
public class AdminResource {
    private final AdminService adminService = new AdminService();

    @GET
    @Path("/")
    public List<Admin> getAllAdmins(){
        return adminService.getAllAdmins();
    }

    @GET
    @Path("/{id}")
    public Admin getAdmin(@PathParam("id") long id){return adminService.getAdmin(id);}

    @POST
    @Path("/")
    public Admin createAdmin(PersonCreateDto createAdmin){
        return adminService.createAdmin(createAdmin);
    }

    @PUT
    @Path("/{id}")
    public Admin updateAdmin(@PathParam("id") long id, PersonUpdateDto personUpdateDto){

        return adminService.updateAdmin(id, personUpdateDto);
    }

    @DELETE
    @Path("/{id}")
    public void deleteAdmin(@PathParam("id") long id){adminService.deleteAdmin(id);}

}
