package com.dolph.Dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dolph.Dao.ContactDao;
import com.dolph.model.Contact;
import com.dolph.model.User;

@Repository("contactDao")
public class ContactDaoImpl extends BaseDaoImpl implements ContactDao {

	@Override
	public List<Contact> findAllContactsByUser(User currentUser, String sSearch) {
		String hql = "select c from Contact c where c.owner.id=? and c.name LIKE ? order by c.name";
		return getSession().createQuery(hql)
				.setParameter(0, currentUser.getId())
				.setParameter(1, "%" + sSearch + "%").list();
	}

	@Override
	public boolean deleteContact(int contactId, User currentUser) {
		String hql = "delete from Contact c where c.id=? and c.owner=?";
		try {
			getSession().createQuery(hql).setParameter(0, contactId)
					.setParameter(1, currentUser).executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Contact> findContactsByPhoneNumber(String phoneNumber) {
		String hql = "select c from Contact c where c.number=?";
		return getSession().createQuery(hql).setParameter(0, phoneNumber)
				.list();
	}
}
