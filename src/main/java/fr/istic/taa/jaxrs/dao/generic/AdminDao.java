package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Admin;

public class AdminDao extends AbstractJpaDao<Long, Admin> {
    public AdminDao() {
        super(Admin.class);
    }
}