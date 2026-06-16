package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.generic.ArtistDao;

import fr.istic.taa.jaxrs.domain.Artist;
import fr.istic.taa.jaxrs.dto.PersonBaseDto;
import fr.istic.taa.jaxrs.dto.PersonUpdateDto;
import fr.istic.taa.jaxrs.dto.PersonCreateDto;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.PathParam;

import java.util.List;

public class ArtistService {
    public Long countArtists(){
        return artistDao.countArtists();
    }
    private Artist fromDtoToArtistMapper(PersonBaseDto personBaseDto, Artist artist) {
        artist.setFirstname(personBaseDto.getFirstname());
        artist.setLastname(personBaseDto.getLastname());
        artist.setEmail(personBaseDto.getEmail());
        artist.setPhone(personBaseDto.getPhone());
        return artist;
    }

    private final ArtistDao artistDao = new ArtistDao();

    public List<Artist> getAllArtists(){
        return artistDao.findAll();
    }

    public Artist getArtist(@PathParam("id") long id){
        return artistDao.findOne(id);
    }

    public Artist createArtist(PersonCreateDto personCreateDto) {
        Artist artist = fromDtoToArtistMapper(personCreateDto, new Artist());
        artistDao.save(artist);
        return artist;
    }

    public Artist updateArtist(long id, PersonUpdateDto personUpdateDto){
        if (!(id == personUpdateDto.getId())) {
            throw new BadRequestException("Artist non accessible");
        }
        Artist artist = fromDtoToArtistMapper(personUpdateDto,getArtist(id));
        artistDao.update(artist);
        return artist;
    }

    public void deleteArtist(long id){
        artistDao.deleteById(id);
    }


}
