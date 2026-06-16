package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.AdminStats;
import fr.istic.taa.jaxrs.service.ArtistService;
import fr.istic.taa.jaxrs.service.ConcertService;
import fr.istic.taa.jaxrs.service.OrganizerService;
import fr.istic.taa.jaxrs.service.TicketService;
import fr.istic.taa.jaxrs.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("stats")
@Produces(MediaType.APPLICATION_JSON)
public class AdminStatsResource {

    private final UserService userService = new UserService();
    private final ConcertService concertService = new ConcertService();
    private final ArtistService artistService = new ArtistService();
    private final OrganizerService organizerService = new OrganizerService();
    private final TicketService ticketService = new TicketService();

    @GET
    @Path("/")
    @Transactional
    public AdminStats getStats() {
        AdminStats stats = new AdminStats();
        stats.setTotalConcerts(concertService.countConcerts());
        stats.setTotalUsers(userService.countUsers());
        stats.setTotalArtists(artistService.countArtists());
        stats.setTotalOrganizers(organizerService.countOrganizers());
        stats.setTotalTickets(ticketService.countTickets());
//        System.out.println("Total concert count: " + stats.getTotalConcerts());
        return stats;
    }
}