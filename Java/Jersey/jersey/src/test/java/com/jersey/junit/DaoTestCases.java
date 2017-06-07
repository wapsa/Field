package com.jersey.junit;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jersey.dao.general.UserDao;
import com.jersey.model.User;
import com.jersey.util.common.GsonProvider;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-common-beans.xml" })
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class DaoTestCases extends GsonProvider {

	@Autowired
	private UserDao userDao;

	// @Test
	public void Test1() {
		User user = userDao.findByEmailAddress("haha@haha.com");
		System.out.println(gson.toJson(user));
	}

}
