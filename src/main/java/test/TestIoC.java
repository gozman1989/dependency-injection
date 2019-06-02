package test;


import org.junit.Assert;
import org.junit.Test;

import ioc.ApplicationContext;
import repository.UserRepository;
import services.UserService;

public class TestIoC {

	
	@Test
	public void test() throws Exception {
		ApplicationContext iocApplicationContext = new ApplicationContext();

		iocApplicationContext.registerClasses(UserRepository.class, UserService.class);

		UserService userService = (UserService) iocApplicationContext.getBean(UserService.class);
		UserRepository userRepository = userService.getUserRepository();
		Assert.assertNotNull(userRepository);
	}

}
