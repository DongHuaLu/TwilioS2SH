package com.dolph.Dao;

import java.util.List;

import com.dolph.model.Contact;
import com.dolph.model.User;

public interface ContactDao extends BaseDao {

	List<Contact> findAllContactsByUser(User currentUser, String sSearch);

	boolean deleteContact(int contactId, User currentUser);

	List<Contact> findContactsByPhoneNumber(String phoneNumber);

}
