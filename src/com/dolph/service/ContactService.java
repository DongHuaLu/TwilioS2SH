package com.dolph.service;

import java.util.List;

import com.dolph.model.Contact;
import com.dolph.model.User;

public interface ContactService {
	public void saveContact(String contactName, String address,
			String contactNumber, User currentUser);

	public List<Contact> findAllContactsByUser(User currentUser, String sSearch);

	public void updateContact(int contactId, String contactName,
			String address, String contactNumber, User currentUser);

	public boolean deleteContact(int contactId, User currentUser);
}
