package com.dolph.Dao;

import com.dolph.model.Validation;

public interface CodeDao extends BaseDao {

	public Validation findVByReceiptient(String receiptient);

}
