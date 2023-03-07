package fr.istic.taa.jaxrs.dao.impl;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Tag;

public class TagDao extends AbstractJpaDao<Long, Tag> {
    public TagDao() {
        this.setClazz(Tag.class);
    }
}
