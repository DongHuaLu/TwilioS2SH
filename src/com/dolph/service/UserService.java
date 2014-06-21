package com.dolph.service;

import com.dolph.model.User;

public interface UserService {
	public User findUserByUserName(String username);
	public User findUserByMobliePhone(String mobilephone);
	public void saveUser(String username, String password, String mobile_phone);
	public void updatePassword(String password, String mobilephone);
	public void updataUser(User user);
}
