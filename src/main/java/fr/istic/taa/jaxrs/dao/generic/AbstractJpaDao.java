package fr.istic.taa.jaxrs.dao.generic;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import static java.util.Objects.requireNonNull;

public abstract class AbstractJpaDao<K, T extends Serializable> implements IGenericDao<K, T> {

	private Class<T> clazz;

	protected EntityManager entityManager;

	public AbstractJpaDao() {
		this.entityManager = EntityManagerHelper.getEntityManager();
	}

	public AbstractJpaDao(Class<T> clazz) {
		this.entityManager = EntityManagerHelper.getEntityManager();
		this.clazz = requireNonNull(clazz);
	}

	public void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	public T findOne(K id) {
//		entityManager.clear();
		return entityManager.find(clazz, id);
	}

	public List<T> findAll() {
		return entityManager.createQuery("select e from " + clazz.getName() + " as e", clazz).getResultList();
	}

	public void save(T entity) {
		EntityTransaction t = this.entityManager.getTransaction();
		boolean isActive = t.isActive();
		if (!isActive) t.begin();
		entityManager.persist(entity);
		if (!isActive) t.commit();
	}

	public T update(final T entity) {
		EntityTransaction t = this.entityManager.getTransaction();
		boolean isActive = t.isActive();
		if (!isActive) t.begin();
		T res = entityManager.merge(entity);
		if (!isActive) t.commit();
		return res;
	}

	public void delete(T entity) {
		EntityTransaction t = this.entityManager.getTransaction();
		boolean isActive = t.isActive();
		if (!isActive) t.begin();
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
		if (!isActive) t.commit();
	}

	public void deleteById(K entityId) {
		T entity = findOne(entityId);
		delete(entity);
	}
}