package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.impl.EtatDao;
import fr.istic.taa.jaxrs.domain.Etat;

import java.util.List;

public class EtatService {
    private final EtatDao etatDao;

    public EtatService() {
        this.etatDao=new EtatDao();
    }
    public Etat getEtat(Long id){
        return etatDao.findOne(id);
    }
    public List<Etat> getEtats(){
        return etatDao.findAll();
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
