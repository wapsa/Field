package com.jersey.dao.common;

import java.io.Serializable;
import java.util.List;

public interface CommonOperations<T extends Serializable> {

	void setClazz(final Class<T> clazzToSet);

	T findOne(final Long id);
	
	T findOne(final String id);

	T findOne(final Serializable id);

	public T loadEntity(final Long id);
	
	public T loadEntity(final String id);

	public T loadEntity(final Serializable id);

	List<T> findAll();

	void persist(final T entity);

	public Object save(final T entity);

	void update(final T entity);

	T merge(final T entity);

	void delete(final T entity);

	void deleteById(final Long entityId);

	void deleteById(final Serializable entityId);

}
