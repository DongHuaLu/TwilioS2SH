package com.dolph.vo;

import com.dolph.model.Contact;

public class ContactVo {

	private int id;
	private String name;
	private String address;
	private String number;

	public ContactVo(Contact contact) {
		this.id = contact.getId();
		this.name = contact.getName();
		this.address = contact.getAddress();
		this.number = contact.getNumber();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
