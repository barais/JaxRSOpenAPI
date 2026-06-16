package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.Concert;
import fr.istic.taa.jaxrs.dto.ConcertCreateDto;
import fr.istic.taa.jaxrs.dto.ConcertUpdateDto;
import fr.istic.taa.jaxrs.service.ConcertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Date;
import java.util.List;

@Path("/concerts")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Concerts", description = "Gestion des concerts")
public class ConcertResource {

    private static final ConcertService concertService = new ConcertService();

    @GET
    @Path("/")
    @Operation(summary = "Récupérer tous les concerts", description = "Retourne la liste de tous les concerts disponibles")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des concerts récupérée avec succès",
                    content = @Content(schema = @Schema(implementation = Concert.class))),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public List<Concert> getConcerts() {
        return concertService.getConcerts();
    }

    @GET
    @Path("/search")
    @Operation(summary = "Rechercher des concerts", description = "Recherche des concerts par artiste, lieu, nom ou genre musical")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Résultats de recherche retournés avec succès"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public List<Concert> searchConcerts(
            @Parameter(description = "Terme de recherche", required = true)
            @QueryParam("q") String searchQ) {
        return concertService.searchConcerts(searchQ);
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Récupérer un concert par ID", description = "Retourne les détails d'un concert spécifique")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Concert trouvé"),
            @ApiResponse(responseCode = "404", description = "Concert non trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public Concert getConcert(
            @Parameter(description = "ID du concert", required = true)
            @PathParam("id") long id) {
        return concertService.getConcert(id);
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Créer un concert", description = "Crée un nouveau concert")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Concert créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public Concert createConcert(
            @Parameter(description = "Données du concert à créer", required = true)
            ConcertCreateDto concertCreateDto) {
        return concertService.createConcert(concertCreateDto);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Modifier un concert", description = "Met à jour les informations d'un concert existant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Concert modifié avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides ou ID incohérent"),
            @ApiResponse(responseCode = "404", description = "Concert non trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public Concert updateConcert(
            @Parameter(description = "ID du concert", required = true)
            @PathParam("id") long id,
            @Parameter(description = "Données de mise à jour", required = true)
            ConcertUpdateDto concertUpdateDto) {
        return concertService.updateConcert(id, concertUpdateDto);
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Supprimer un concert", description = "Supprime un concert par son ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Concert supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Concert non trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public void deleteConcert(
            @Parameter(description = "ID du concert à supprimer", required = true)
            @PathParam("id") long id) {
        concertService.deleteConcert(id);
    }

    @GET
    @Path("/location")
    @Operation(summary = "Rechercher par lieu", description = "Retourne les concerts se déroulant à un lieu spécifique")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Concerts trouvés"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public List<Concert> getConcertsByLocation(
            @Parameter(description = "Lieu du concert", required = true)
            @QueryParam("q") String location) {
        return concertService.findByLocation(location);
    }

    @GET
    @Path("/validated")
    @Operation(summary = "Concerts validés", description = "Retourne uniquement les concerts validés par un administrateur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des concerts validés"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public List<Concert> getValidatedConcerts() {
        return concertService.findValidated();
    }

    @GET
    @Path("/maxprice")
    @Operation(summary = "Filtrer par prix maximum", description = "Retourne les concerts dont le prix est inférieur ou égal au montant donné")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des concerts filtrés par prix"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public List<Concert> getConcertsByMaxPrice(
            @Parameter(description = "Prix maximum en euros", required = true)
            @QueryParam("price") Long maxPrice) {
        return concertService.findByMaxPrice(maxPrice);
    }

    @GET
    @Path("/sortByPopularity")
    @Operation(summary = "Trier par popularité", description = "Retourne les concerts triés par popularité décroissante")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Concerts triés par popularité")
    })
    public List<Concert> getConcertsOrderByPopularity() {
        return concertService.findAllOrderByPopularity();
    }

    @GET
    @Path("/sortByPrice")
    @Operation(summary = "Trier par prix", description = "Retourne les concerts triés par prix croissant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Concerts triés par prix")
    })
    public List<Concert> getConcertsOrderByPrice() {
        return concertService.findAllOrderByPrice();
    }

    @GET
    @Path("/sortByDate")
    @Operation(summary = "Trier par date", description = "Retourne les concerts triés par date croissante")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Concerts triés par date")
    })
    public List<Concert> getConcertsOrderByDate() {
        return concertService.findAllOrderByDate();
    }

    @GET
    @Path("/date")
    @Operation(summary = "Rechercher par date", description = "Retourne les concerts à une date spécifique")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Concerts trouvés pour cette date")
    })
    public List<Concert> getConcertsByDate(
            @Parameter(description = "Date au format yyyy-MM-dd", required = true)
            @QueryParam("q") Date date) {
        return concertService.findByDate(date);
    }
}