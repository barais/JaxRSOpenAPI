package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Concert;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.Date;

import java.util.List;

public class ConcertDao extends AbstractJpaDao<Long, Concert> {

    public ConcertDao() {
        super(Concert.class);
    }
    //Requête jpql
    public List<Concert> searchConcerts(String searchQ) {
        String q = "SELECT DISTINCT c FROM Concert c " +
                "LEFT JOIN c.artists a " +
                "WHERE LOWER(a.firstname) LIKE LOWER(:searchQ) " +
                "OR LOWER(a.lastname) LIKE LOWER(:searchQ) " +
                "OR LOWER(c.name) LIKE LOWER(:searchQ) " +
                "OR LOWER(c.location) LIKE LOWER(:searchQ) " +
                "OR LOWER(c.musicalGenre) LIKE LOWER(:searchQ) ";
        return entityManager.createQuery(q, Concert.class)
                .setParameter("searchQ", "%" + searchQ + "%")
                .getResultList();
    }

    // Requête nommée
    public List<Concert> findByLocation(String location) {
        return entityManager.createNamedQuery("Concert.findByLocation", Concert.class)
                .setParameter("location", location)
                .getResultList();
    }

    public List<Concert> findValidated() {
        return entityManager.createNamedQuery("Concert.findValidated", Concert.class)
                .getResultList();
    }

    // Criteria Query
    public List<Concert> findByMaxPrice(Long maxPrice) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Concert> cq = cb.createQuery(Concert.class);
        Root<Concert> root = cq.from(Concert.class);
        cq.select(root).where(cb.lessThanOrEqualTo(root.get("price"), maxPrice));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<Concert> findByDate(Date date) {
        return entityManager.createNamedQuery("Concert.findByDate", Concert.class)
                .setParameter("date", date)
                .getResultList();
    }

    public List<Concert> findAllOrderByPopularity() {
        return entityManager.createNamedQuery("Concert.findAllOrderByPopularity", Concert.class)
                .getResultList();
    }

    public List<Concert> findAllOrderByPrice() {
        return entityManager.createNamedQuery("Concert.findAllOrderByPrice", Concert.class)
                .getResultList();
    }

    public List<Concert> findAllOrderByDate() {
        return entityManager.createNamedQuery("Concert.findAllOrderByDate", Concert.class)
                .getResultList();
    }

    public Long countConcerts(){
        Long count = entityManager.createQuery(
                        "SELECT COUNT(c) FROM Concert c",
                        Long.class)

                .getSingleResult();

        entityManager.clear();
        return count;
    }

}