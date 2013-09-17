package com.dolph.model;

import java.util.Date;

public class Validation {
	private int id;
	private String receiptient;
	private String code;
	private Date sendTime;

	public Validation(String receiptient, String code, Date sendTime) {
		super();
		this.receiptient = receiptient;
		this.code = code;
		this.sendTime = sendTime;
	}

	public Validation() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReceiptient() {
		return receiptient;
	}

	public void setReceiptient(String receiptient) {
		this.receiptient = receiptient;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

}
