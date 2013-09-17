package com.dolph.model;

import java.util.HashSet;
import java.util.Set;

public class User {
	private int id;
	private String username;
	private String password;
	private String mobile_phone;
	private String twilio_number;
	private boolean status;
	private String deviceId;
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	private Set<Contact> contacts = new HashSet<Contact>();

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile_phone() {
		return mobile_phone;
	}

	public void setMobile_phone(String mobile_phone) {
		this.mobile_phone = mobile_phone;
	}

	public String getTwilio_number() {
		return twilio_number;
	}

	public void setTwilio_number(String twilio_number) {
		this.twilio_number = twilio_number;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
