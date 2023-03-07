package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.Etat;
import fr.istic.taa.jaxrs.dto.EtatDto;
import fr.istic.taa.jaxrs.service.EtatService;

import javax.ws.rs.*;
import java.util.List;

@Path("/etats")
@Produces({"application/json"})
public class EtatResource {
    EtatService service = new EtatService();

    public EtatResource() {
    }
    @GET
    @Path("/{id}")
    public EtatDto getEtat(@PathParam("id") Long id){
        return service.getEtat(id);
    }
    @GET
    @Path("/")
    public List<EtatDto> getList(){
        return service.getEtats();
    }

    @POST
    @Consumes("application/json")
    public void createEtat(Etat etat){
        service.addEtat(etat);
    }

    @DELETE
    @Path("/{id}")
    public void deleteEtat(@PathParam("id")Long id){
        service.removeEtat(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    public void updateEtat(@PathParam("id")Long id, Etat etat){
        service.updateEtat(id,etat);
    }
}
