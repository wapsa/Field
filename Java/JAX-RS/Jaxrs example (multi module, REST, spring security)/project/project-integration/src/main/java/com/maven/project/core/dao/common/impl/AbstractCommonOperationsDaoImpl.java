package com.maven.project.core.dao.common.impl;

// Imports--------------------------------------------------
import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.maven.project.core.dao.common.CommonOperations;

@SuppressWarnings("unchecked")
public abstract class AbstractCommonOperationsDaoImpl<T extends Serializable> implements CommonOperations<T> {

	// Constructors--------------------------------------------------
	public AbstractCommonOperationsDaoImpl() {
	}

	// Properties--------------------------------------------------
	private Class<?> clazz;

	// Variables, Objects, References-------------------------------------
	@Autowired
	private SessionFactory sessionFactory;

	// Getters & Setters--------------------------------------------------
	public final void setClazz(final Class<T> clazzToSet) {
		clazz = clazzToSet;
	}

	public T findOne(final long id) {
		return (T) getCurrentSession().get(clazz, id);
	}

	public T findOne(final Serializable id) {
		return (T) getCurrentSession().get(clazz, id);
	}

	public Long save(final T entity) {
		return (Long) getCurrentSession().save(entity);
	}

	protected final Session getCurrentSession() {
		Session session = sessionFactory.getCurrentSession();
		// session.enableFilter("filterRemoved").setParameter("suppliedRemoved",
		// new Boolean(false));
		return session;
	}

}
