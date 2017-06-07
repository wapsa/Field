package com.jersey.junit;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jersey.dto.request.UserProfileIn;
import com.jersey.service.CommonService;
import com.jersey.util.common.GsonProvider;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-common-beans.xml" })
public class ServiceTestCases extends GsonProvider {

	@Autowired
	private CommonService commonService;

	// @Test
	public void Test1() {
		Assert.assertEquals(1,
				commonService.saveUserProfile(
						RequestBuilder.start(UserProfileIn::new).with(UserProfileIn::setEmailAddress, "svp91@gmail.com")
								.with(UserProfileIn::setFullName, "Saurabh Pawar").build())
						.getSuccess());
	}

}
