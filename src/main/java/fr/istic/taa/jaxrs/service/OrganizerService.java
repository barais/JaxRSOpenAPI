package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.generic.OrganizerDao;
import fr.istic.taa.jaxrs.domain.Concert;

import fr.istic.taa.jaxrs.domain.Organizer;
import fr.istic.taa.jaxrs.dto.ConcertBaseDto;
import fr.istic.taa.jaxrs.dto.ConcertCreateDto;
import fr.istic.taa.jaxrs.dto.PersonBaseDto;
import fr.istic.taa.jaxrs.dto.PersonUpdateDto;
import fr.istic.taa.jaxrs.dto.PersonCreateDto;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.PathParam;

import java.util.List;

public class OrganizerService {

    public Long countOrganizers(){
        return organizerDao.countOrganizers();
    }

    private Organizer fromDtoToOrganizerMapper(PersonBaseDto personBaseDto, Organizer organizer) {
        organizer.setFirstname(personBaseDto.getFirstname());
        organizer.setLastname(personBaseDto.getLastname());
        organizer.setEmail(personBaseDto.getEmail());
        organizer.setPhone(personBaseDto.getPhone());
        return organizer;
    }

    private final OrganizerDao organizerDao = new OrganizerDao();

    public List<Organizer> getAllOrganizers(){
        return organizerDao.findAll();
    }

    public Organizer getOrganizer(@PathParam("id") long id){
        return organizerDao.findOne(id);
    }

    public Organizer createOrganizer(PersonCreateDto personCreateDto) {
        Organizer organizer = fromDtoToOrganizerMapper(personCreateDto, new Organizer());
        organizerDao.save(organizer);
        return organizer;
    }

    public Organizer updateOrganizer(long id, PersonUpdateDto personUpdateDto){
        if (!(id == personUpdateDto.getId())) {
            throw new BadRequestException("Organizer non accessible");
        }
        Organizer organizer = fromDtoToOrganizerMapper(personUpdateDto,getOrganizer(id));
        organizerDao.update(organizer);
        return organizer;
    }

    public void deleteOrganizer(long id){
        organizerDao.deleteById(id);
    }


}
