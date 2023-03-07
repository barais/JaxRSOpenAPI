package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.impl.EtatDao;
import fr.istic.taa.jaxrs.dao.impl.EtatDao;
import fr.istic.taa.jaxrs.domain.Etat;
import fr.istic.taa.jaxrs.domain.Etat;
import fr.istic.taa.jaxrs.dto.EtatDto;

import java.util.ArrayList;
import java.util.List;

public class EtatService {
    EtatDao etatDao = new EtatDao();

    public EtatService() {
    }
    public EtatDto getEtat(Long id){
        Etat etat = etatDao.findOne(id);
        return new EtatDto(etat.getLibelle(),etat.getTickets());
    }
    public List<EtatDto> getEtats(){
        List<EtatDto> etats = new ArrayList<>();
        for(Etat etat:etatDao.findAll()){
            EtatDto etatDto = new EtatDto(etat.getLibelle(),etat.getTickets());
            etats.add(etatDto);
        }
        return etats;
    }
    public void addEtat(Etat etat){
        etatDao.save(etat);
    }
    public void removeEtat(Long id){
        etatDao.deleteById(id);
    }

    public void updateEtat(Long id, Etat etat){
        if(etatDao.findOne(id)==null){
            throw new RuntimeException("Aucun etat trouvé");
        }
        else{
            Etat update = etatDao.findOne(id);
            update.setLibelle(etat.getLibelle());
            etatDao.update(update);
        }
    }
}
