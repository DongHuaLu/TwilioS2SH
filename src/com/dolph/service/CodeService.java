package com.dolph.service;

public interface CodeService {

	public String queryReceiptientCode(String receiptient);

	public void insertOrUpdateCode(String newCode, String mobile_phone);

	public boolean isRegister(String mobile_phone);

}
