package fr.istic.taa.jaxrs.dao.generic;
import fr.istic.taa.jaxrs.domain.User;

public class UserDao  extends AbstractJpaDao<Long, User>{
    public UserDao() {
        super(User.class);
    }

    public Long countUsers() {
        Long count = entityManager.createQuery(
                        "SELECT COUNT(p) FROM Person p WHERE TYPE(p) = User",
                        Long.class)
                .getSingleResult();
        entityManager.clear();
        return count;
    }
}
