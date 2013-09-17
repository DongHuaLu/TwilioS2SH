package com.dolph.model;

public class Contact {
	private int id;
	private String name;
	private String address;
	private String number;
	private User owner;

	public Contact() {
	}

	public Contact(String name, String address, String number, User owner) {
		super();
		this.name = name;
		if (address == null) {
			address = "";
		}
		this.address = address;
		if (number == null) {
			number = "";
		}
		this.number = number;
		this.owner = owner;
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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "{'id':'" + id + "','name':'" + name + "','address':'" + address
				+ "','number':'" + number + "'}";
	}
}
