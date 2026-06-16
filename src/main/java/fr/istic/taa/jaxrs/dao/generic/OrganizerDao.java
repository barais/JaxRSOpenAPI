package fr.istic.taa.jaxrs.dao.generic;


import fr.istic.taa.jaxrs.domain.Admin;
import fr.istic.taa.jaxrs.domain.Organizer;

public class OrganizerDao extends AbstractJpaDao<Long, Organizer>{
    public OrganizerDao() {
        super(Organizer.class);
    }

    public Long countOrganizers() {
        Long count = entityManager.createQuery(
                        "SELECT COUNT(p) FROM Person p WHERE TYPE(p) = Organizer",
                        Long.class)
                .getSingleResult();
        entityManager.clear();
        return count;
    }
}
