package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Admin;
import fr.istic.taa.jaxrs.domain.Artist;

public class ArtistDao extends AbstractJpaDao<Long, Artist> {
    public ArtistDao() {
        super(Artist.class);
    }

    public Long countArtists() {
        Long count =  entityManager.createQuery(
                        "SELECT COUNT(p) FROM Person p WHERE TYPE(p) = Artist",
                        Long.class)
                .getSingleResult();
        entityManager.clear();
        return count;
    }
}
