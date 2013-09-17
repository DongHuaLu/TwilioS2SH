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

}
