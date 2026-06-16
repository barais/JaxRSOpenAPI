package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.Notification;
import fr.istic.taa.jaxrs.service.NotificationService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("notifications")
@Produces(MediaType.APPLICATION_JSON)
public class NotificationResource {
    private final NotificationService notificationService = new NotificationService();

    @GET
    @Path("/")
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @GET
    @Path("/{id}")
    public Notification getNotification(@PathParam("id") long id) {
        return notificationService.getNotification(id);
    }

    @DELETE
    @Path("/{id}")
    public void deleteNotification(@PathParam("id") long id) {
        notificationService.deleteNotification(id);
    }
}