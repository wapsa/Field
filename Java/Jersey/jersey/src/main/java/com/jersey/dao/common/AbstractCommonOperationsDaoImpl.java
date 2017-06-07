package com.jersey.dao.common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("unchecked")
public abstract class AbstractCommonOperationsDaoImpl<T extends Serializable> implements CommonOperations<T> {

	public AbstractCommonOperationsDaoImpl() {
	}

	private Class<?> clazz;

	@Autowired
	private SessionFactory sessionFactory;

	public final void setClazz(final Class<T> clazzToSet) {
		clazz = clazzToSet;
	}

	public T findOne(final Long id) {
		return (T) getCurrentSession().get(clazz, id);
	}

	public T findOne(final String id) {
		return (T) getCurrentSession().get(clazz, id);
	}

	public T findOne(final Serializable id) {
		return (T) getCurrentSession().get(clazz, id);
	}

	/**
	 * You should not use this method to determine if an instance exists (use
	 * get() instead). Use this only to retrieve an instance that you assume
	 * exists, where non-existence would be an actual error.
	 * 
	 * <b>Use load() when you need to obtain a reference to the object without
	 * issuing extra SQL queries, for example, to create a relationship with
	 * another object.</b>
	 */
	public T loadEntity(final Long id) {
		return (T) getCurrentSession().load(clazz, id);
	}
	
	public T loadEntity(final String id) {
		return (T) getCurrentSession().load(clazz, id);
	}

	public T loadEntity(final Serializable id) {
		return (T) getCurrentSession().load(clazz, id);
	}

	public List<T> findAll() {
		return getCurrentSession().createQuery("from " + clazz.getName()).list();
	}

	public void persist(final T entity) {
		getCurrentSession().persist(entity);
	}

	public Object save(final T entity) {
		return getCurrentSession().save(entity);
	}

	public T merge(final T entity) {
		return (T) getCurrentSession().merge(entity);
	}

	public void update(final T entity) {
		getCurrentSession().update(entity);
	}

	public void delete(final T entity) {
		getCurrentSession().delete(entity);
	}

	public void deleteById(final Long entityId) {
		final T entity = findOne(entityId);
		if (entity != null) {
			delete(entity);
		}
	}

	public void deleteById(final Serializable entityId) {
		final T entity = findOne(entityId);
		if (entity != null) {
			delete(entity);
		}
	}

	protected final Session getCurrentSession() {
		Session session = sessionFactory.getCurrentSession();
		//session.enableFilter("filterDeleted").setParameter("suppliedIsDeleted", new Boolean(false));
		return session;
	}

}
