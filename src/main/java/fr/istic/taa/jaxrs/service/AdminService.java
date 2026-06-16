package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.generic.AdminDao;
import fr.istic.taa.jaxrs.domain.Concert;

import fr.istic.taa.jaxrs.domain.Admin;
import fr.istic.taa.jaxrs.dto.ConcertBaseDto;
import fr.istic.taa.jaxrs.dto.ConcertCreateDto;
import fr.istic.taa.jaxrs.dto.PersonBaseDto;
import fr.istic.taa.jaxrs.dto.PersonUpdateDto;
import fr.istic.taa.jaxrs.dto.PersonCreateDto;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.PathParam;

import java.util.List;

public class AdminService {

    private Admin fromDtoToAdminMapper(PersonBaseDto personBaseDto, Admin admin)
    {
        admin.setFirstname(personBaseDto.getFirstname());
        admin.setLastname(personBaseDto.getLastname());
        return admin;
    }

    private final AdminDao adminDao = new AdminDao();

    public List<Admin> getAllAdmins(){
        return adminDao.findAll();
    }

    public Admin getAdmin(@PathParam("id") long id){
        return adminDao.findOne(id);
    }

    public Admin createAdmin(PersonCreateDto personCreateDto) {
        Admin admin = fromDtoToAdminMapper(personCreateDto, new Admin());
        adminDao.save(admin);
        return admin;
    }

    public Admin updateAdmin(long id, PersonUpdateDto personUpdateDto){
        if (!(id == personUpdateDto.getId())) {
            throw new BadRequestException("Admin non accessible");
        }
        Admin admin = fromDtoToAdminMapper(personUpdateDto,getAdmin(id));
        adminDao.update(admin);
        return admin;
    }

    public void deleteAdmin(long id){
        adminDao.deleteById(id);
    }

}
