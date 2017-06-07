package com.maven.project.core.dao.impl;

// Imports--------------------------------------------------
import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.maven.project.core.dao.UserDao;
import com.maven.project.core.dao.common.impl.AbstractCommonOperationsDaoImpl;
import com.maven.project.hib.model.User;

/**
 * Here @Transactional(propagation = Propagation.MANDATORY) represents that
 * transaction must be started at service layer and the DAO layer will use that.
 * DAO layer must not start any transaction.
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class UserDaoImpl extends AbstractCommonOperationsDaoImpl<User> implements UserDao {

	// Properties--------------------------------------------------
	Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	// Constructors--------------------------------------------------
	public UserDaoImpl() {
		super();
		setClazz(User.class);
	}

	// Methods--------------------------------------------------

	public User findByUserName(String username) {
		Query query = getCurrentSession().getNamedQuery("HQL_GET_USER_BY_USERNAME");
		query.setString("suppliedUserName", username);
		return (User) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> fetchUserNameIfExists(String userName) {
		Query query = getCurrentSession().getNamedQuery("HQL_GET_USERNAME_IF_EXISTS");
		query.setString("suppliedUserName", userName);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> fetchActivationExpiryTime(Long userId, String activationCode) {
		Query query = getCurrentSession().getNamedQuery("HQL_GET_USER_ID_ACTIVATION_CODE_IF_EXISTS");
		query.setLong("suppliedUserId", userId);
		query.setString("suppliedActivationCode", activationCode);
		return query.list();
	}

	@Override
	public void deleteActivationEntity(Long userId) {
		Query query = getCurrentSession().getNamedQuery("HQL_DELETE_ACTIVATION_ENTITY");
		query.setLong("suppliedUserId", userId);
		query.executeUpdate();

	}

}
