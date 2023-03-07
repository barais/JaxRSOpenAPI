package fr.istic.taa.jaxrs.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import fr.istic.taa.jaxrs.domain.Pet;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.ArrayList;
import java.util.List;

@Path("/pet")
@Produces({"application/json", "application/xml"})
public class PetResource {

  @GET
  @Path("/{petId}")
  public Pet getPetById(@PathParam("petId") Long petId)  {
      // return pet
      return new Pet();
  }
  @GET
  @Path("/")
  public List<Pet> getPets()  {
    // return pet
    List<Pet> pets = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      Pet pet = new Pet();
      pet.setId(i);
      pets.add(pet);
    }
    return pets;
  }

  @POST
  @Consumes("application/json")
  public Response addPet(
      @Parameter(description = "Pet object that needs to be added to the store", required = true) Pet pet) {
    // add pet
    return Response.ok().entity("SUCCESS").build();
  }
}