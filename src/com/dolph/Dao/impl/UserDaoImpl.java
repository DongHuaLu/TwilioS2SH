package com.dolph.Dao.impl;

import org.springframework.stereotype.Repository;

import com.dolph.Dao.UserDao;
import com.dolph.model.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	public User findUserByUsername(String username) {
		return (User) getSession()
				.createQuery("select u from User u where u.username=?")
				.setParameter(0, username).uniqueResult();
	}

	public User findUserByMobliePhone(String mobilephone) {
		return (User) getSession()
				.createQuery("select u from User u where u.mobile_phone=?")
				.setParameter(0, mobilephone).uniqueResult();
	}

}
