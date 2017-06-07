package com.jersey.dao.general;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.jersey.dao.common.AbstractCommonOperationsDaoImpl;
import com.jersey.model.User;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class UserDaoImpl extends AbstractCommonOperationsDaoImpl<User> implements UserDao {

	Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	public UserDaoImpl() {
		super();
		setClazz(User.class);
	}

	@Override
	public User findByEmailAddress(String emailAddress) {
		Assert.hasText(emailAddress);
		return (User) getCurrentSession().getNamedQuery("FIND_USER_BY_EMAIL_ADDRESS")
				.setParameter("suppliedEmailAddress", emailAddress).uniqueResult();
	}
}
