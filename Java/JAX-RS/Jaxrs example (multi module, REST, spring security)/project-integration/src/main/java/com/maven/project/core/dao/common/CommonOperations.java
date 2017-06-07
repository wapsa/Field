package com.maven.project.core.dao.common;

// Imports--------------------------------------------------
import java.io.Serializable;

public interface CommonOperations<T extends Serializable> {

	// Methods--------------------------------------------------
	void setClazz(final Class<T> clazzToSet);

	T findOne(final long id);

	T findOne(final Serializable id);

	public Long save(final T entity);

}
