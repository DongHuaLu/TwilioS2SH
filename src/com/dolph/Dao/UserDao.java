package com.dolph.Dao;

import com.dolph.model.User;

public interface UserDao extends BaseDao {
	public User findUserByUsername(String username);

	public User findUserByMobliePhone(String mobilephone);
}
