package com.dolph.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dolph.Dao.UserDao;
import com.dolph.model.User;
import com.dolph.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static UserDao userDao;

	public User findUserByUserName(String username) {
		return userDao.findUserByUsername(username);
	}

	public User findUserByMobliePhone(String mobilephone) {
		return userDao.findUserByMobliePhone(mobilephone);
	}

	public void saveUser(String username, String password, String mobile_phone) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setMobile_phone(mobile_phone);
		userDao.save(user);
	}

	public void updatePassword(String password, String mobilephone) {
		User user = findUserByMobliePhone(mobilephone);
		user.setPassword(password);
		userDao.save(user);
	}

	@Resource
	public void setMenuDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void updataUser(User user) {
		userDao.update(user);
	}

}
