package com.dolph.init;

import junit.framework.TestCase;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dolph.model.Contact;
import com.dolph.model.User;
import com.dolph.service.ContactService;
import com.dolph.service.UserService;

public class Init extends TestCase {

	public void testUser() {
		BeanFactory bf = new ClassPathXmlApplicationContext("beans.xml");
		UserService userService = (UserService) bf.getBean("userService");
		ContactService contactService = (ContactService) bf.getBean("contactService");
		for (int i = 0; i < 50; i++) {
			userService.saveUser(i + "10", "123", i + "10");
			User u=userService.findUserByUserName(i + "10");
			for (int j = 0; j < 50; j++) {
				contactService.saveContact(i + "0" + j, "address", i + "0" + j, u);
			}
		}
	}
}
