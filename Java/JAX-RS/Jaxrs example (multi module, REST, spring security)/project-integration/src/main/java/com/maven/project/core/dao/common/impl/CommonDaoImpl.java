package com.maven.project.core.dao.common.impl;

// Imports--------------------------------------------------
import java.io.Serializable;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.maven.project.core.dao.common.CommonDao;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional(propagation = Propagation.MANDATORY)
public class CommonDaoImpl<T extends Serializable> extends AbstractCommonOperationsDaoImpl<T> implements CommonDao<T> {

}
