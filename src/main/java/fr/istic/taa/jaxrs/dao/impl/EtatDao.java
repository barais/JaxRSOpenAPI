package fr.istic.taa.jaxrs.dao.impl;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Etat;

public class EtatDao extends AbstractJpaDao<Long, Etat> {
    public EtatDao() {
        this.setClazz(Etat.class);
    }
}
