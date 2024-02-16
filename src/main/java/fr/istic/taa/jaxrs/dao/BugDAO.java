package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Bug;
import fr.istic.taa.jaxrs.domain.Feature;

public class BugDAO extends AbstractJpaDao<Long, Bug> {

    public BugDAO() {
        setClazz(Bug.class);
    }
}
