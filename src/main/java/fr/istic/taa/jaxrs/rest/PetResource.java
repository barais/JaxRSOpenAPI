package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.generic.EntityManagerHelper;
import fr.istic.taa.jaxrs.domain.Pet;
import fr.istic.taa.jaxrs.domain.User;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("pet")
@Produces({"application/json", "application/xml"})
public class PetResource {

  @GET
  @Path("/{petId}")
  public Pet getPetById(@PathParam("petId") Long petId)  {
      // return pet
    EntityManager manager = EntityManagerHelper.getEntityManager();
    EntityTransaction tx = manager.getTransaction();
    tx.begin();
    Pet pet = new Pet();
    manager.persist(pet);
    tx.commit();
    return new Pet();
  }

  @POST
  @Consumes("application/json")
  public Response addPet(
      @Parameter(description = "Pet object that needs to be added to the store", required = true) Pet pet) {
    // add pet
    return Response.ok().entity("SUCCESS").build();
  }
  @POST
  @Path("/new_pet")
  public Response newPet() {
    EntityManager manager = EntityManagerHelper.getEntityManager();
    EntityTransaction tx = manager.getTransaction();
    tx.begin();
    Pet pet = new Pet();
    // Configurer les données du nouveau pet si nécessaire
    manager.persist(pet);
    User user = new User();
    manager.persist(user);
    tx.commit();
    return Response.ok().entity("SUCCESS").build();
  }
}