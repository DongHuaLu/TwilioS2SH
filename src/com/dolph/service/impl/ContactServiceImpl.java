package com.dolph.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dolph.Dao.ContactDao;
import com.dolph.model.Contact;
import com.dolph.model.User;
import com.dolph.service.ContactService;

@Service("contactService")
public class ContactServiceImpl implements ContactService {
	private ContactDao contactDao;

	@Resource
	public void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}

	public void saveContact(String contactName, String address,
			String contactNumber, User currentUser) {
		Contact contact = new Contact(contactName, address, contactNumber,
				currentUser);
		contactDao.save(contact);

	}

	@Override
	public List<Contact> findAllContactsByUser(User currentUser, String sSearch) {
		if (sSearch == null) {
			sSearch = "";
		}
		return contactDao.findAllContactsByUser(currentUser, sSearch);
	}

	@Override
	public void updateContact(int contactId, String contactName,
			String address, String contactNumber, User currentUser) {
		Contact contact = new Contact(contactName, address, contactNumber,
				currentUser);
		contact.setId(contactId);
		contactDao.update(contact);

	}

	@Override
	public boolean deleteContact(int contactId, User currentUser) {
		return contactDao.deleteContact(contactId,currentUser);
		
	}
}
