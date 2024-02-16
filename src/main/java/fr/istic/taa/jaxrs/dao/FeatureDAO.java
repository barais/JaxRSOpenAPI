package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Feature;
import fr.istic.taa.jaxrs.domain.User;

public class FeatureDAO extends AbstractJpaDao<Long, Feature> {

    public FeatureDAO() {
        setClazz(Feature.class);
    }
}
