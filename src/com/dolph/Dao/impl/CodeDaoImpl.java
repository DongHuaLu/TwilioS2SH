package com.dolph.Dao.impl;

import org.springframework.stereotype.Repository;

import com.dolph.Dao.CodeDao;
import com.dolph.model.Validation;

@Repository("codeDao")
public class CodeDaoImpl extends BaseDaoImpl implements CodeDao {

	@Override
	public Validation findVByReceiptient(String receiptient) {
		return (Validation) getSession()
				.createQuery("select v from Validation v where v.receiptient=?")
				.setParameter(0, receiptient).uniqueResult();
		
	}
}
