package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.generic.ArtistDao;
import fr.istic.taa.jaxrs.dao.generic.ConcertDao;
import fr.istic.taa.jaxrs.dao.generic.OrganizerDao; // Pense à ajouter l'import de ton OrganizerDao
import fr.istic.taa.jaxrs.domain.Artist;
import fr.istic.taa.jaxrs.domain.Concert;
import fr.istic.taa.jaxrs.domain.Organizer;
import fr.istic.taa.jaxrs.dto.ConcertBaseDto;
import fr.istic.taa.jaxrs.dto.ConcertCreateDto;
import fr.istic.taa.jaxrs.dto.ConcertUpdateDto;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class ConcertService {
    private static final ConcertDao concertDao = new ConcertDao();
    private static final OrganizerDao organizerDao = new OrganizerDao(); // Ajout du DAO Organisateur
    private static final ArtistDao artistDao = new ArtistDao();           // Ajout du DAO Artiste

    public Long countConcerts(){
        return concertDao.countConcerts();
    }

    /**
     * Ton mapper mis à jour avec les nouveaux champs et la gestion des relations (ManyToMany, ManyToOne)
     */
    private Concert fromDtoToConcertMapper(ConcertBaseDto concertDto, Concert concert) {
        // 1. Mapping des champs que tu avais déjà définis
        concert.setName(concertDto.getName());
        concert.setImage(concertDto.getImage());
        concert.setDescription(concertDto.getDescription());
        concert.setLocation(concertDto.getLocation());
        concert.setPrice(concertDto.getPrice());
        concert.setPopularity(concertDto.getPopularity());
        concert.setPlaceNumber(concertDto.getPlaceNumber());
        concert.setMusicalGenre(concertDto.getMusicalGenre());
        concert.setDate(concertDto.getDate());
        concert.setStartTime(concertDto.getStartTime());
        concert.setEndTime(concertDto.getEndTime());

        // 3. Récupération et liaison de l'Organisateur
        if (concertDto.getOrganizerId() != null) {
            Organizer organizer = organizerDao.findOne(concertDto.getOrganizerId());
            if (organizer != null) {
                concert.setOrganizer(organizer);
            } else {
                throw new NotFoundException("Organisateur introuvable avec l'ID : " + concertDto.getOrganizerId());
            }
        }

        // 4. Récupération et liaison de la liste des Artistes (Relation @ManyToMany)
        if (concertDto.getArtistIds() != null && !concertDto.getArtistIds().isEmpty()) {
            Collection<Artist> artistsEntities = new ArrayList<>();
            for (Long artistId : concertDto.getArtistIds()) {
                Artist artist = artistDao.findOne(artistId);
                if (artist != null) {
                    artistsEntities.add(artist);

                    // Après avoir rajouter parmi la liste des artistes invités au concert ,
                    //Nous souhaitons rajouter egalement le concert à la liste des concerts pour chaque artiste
                    if (artist.getConcerts() == null) {
                        artist.setConcerts(new ArrayList<>());
                    }
                    if (!artist.getConcerts().contains(concert)) {
                        artist.getConcerts().add(concert);
                    }
                }
            }
            concert.setArtists(artistsEntities);
        } else {
            // Si aucun artiste n'est envoyé, on vide ou on initialise la collection
            concert.setArtists(new ArrayList<>());
        }

        return concert;
    }

    public List<Concert> getConcerts() {
        return concertDao.findAll();
    }

    public Concert getConcert(Long id) {
        return concertDao.findOne(id);
    }

    public Concert createConcert(ConcertCreateDto concertCreateDto) {
        Concert concert = fromDtoToConcertMapper(concertCreateDto, new Concert());
        // Les billets dispo sont égaux au nombre de places à la création
        concert.setAvailableTickets(concert.getPlaceNumber());

        // Valeurs par défaut pour l'état d'un nouveau concert
        concert.setIsValidated(false);
        concert.setIsCanceled(false);
        concert.setIsDeleted(false);

        concertDao.save(concert);
        return concert;
    }

    public Concert updateConcert(long id, ConcertUpdateDto concertUpdateDto) {
        if (!(id == concertUpdateDto.getId())) {
            throw new BadRequestException("Concert non accessible");
        }
        // Ton mapper mettra automatiquement à jour l'organisateur ou les artistes si modifiés dans le formulaire
        Concert concert = fromDtoToConcertMapper(concertUpdateDto, getConcert(id));
        concertDao.update(concert);
        return concert;
    }

    public List<Concert> searchConcerts(String searchQ) {
        return concertDao.searchConcerts(searchQ);
    }

    public void deleteConcert(Long id) {
        concertDao.deleteById(id);
    }

    public List<Concert> findByLocation(String location) {
        return concertDao.findByLocation(location);
    }

    public List<Concert> findValidated() {
        return concertDao.findValidated();
    }

    public List<Concert> findByMaxPrice(Long maxPrice) {
        return concertDao.findByMaxPrice(maxPrice);
    }

    public List<Concert> findByDate(Date date) {
        return concertDao.findByDate(date);
    }

    public List<Concert> findAllOrderByPopularity() {
        return concertDao.findAllOrderByPopularity();
    }

    public List<Concert> findAllOrderByPrice() {
        return concertDao.findAllOrderByPrice();
    }

    public List<Concert> findAllOrderByDate() {
        return concertDao.findAllOrderByDate();
    }
}