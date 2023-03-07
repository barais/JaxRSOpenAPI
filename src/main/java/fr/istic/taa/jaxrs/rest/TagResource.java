package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.Tag;
import fr.istic.taa.jaxrs.dto.TagDto;
import fr.istic.taa.jaxrs.service.TagService;

import javax.ws.rs.*;
import java.util.List;

@Path("/tags")
@Produces({"application/json"})
public class TagResource {
    TagService service = new TagService();

    public TagResource() {
    }
    @GET
    @Path("/{id}")
    public TagDto getTag(@PathParam("id") Long id){
        return service.getTag(id);
    }
    @GET
    @Path("/")
    public List<TagDto> getList(){
        return service.getTags();
    }

    @POST
    @Consumes("application/json")
    public void createTag(Tag tag){
        service.addTag(tag);
    }

    @DELETE
    @Path("/{id}")
    public void deleteTag(@PathParam("id")Long id){
        service.removeTag(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    public void updateTag(@PathParam("id")Long id, Tag tag){
        service.updateTag(id,tag);
    }
}
