package com.dolph.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Record {
	private int id;
	private String number;
	private String name;
	private Date startTime;
	private int duration;
	private User owner;

	public Record() {
	}

	public Record(String number, String name, Date startTime, int duration,
			User owner) {
		super();
		this.number = number;
		this.name = name;
		this.startTime = startTime;
		this.duration = duration;
		this.owner = owner;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "{'id':'" + id + "','name':'" + name + "','duration':'"
				+ duration + "','number':'" + number + "','startTime':'"
				+ df.format(startTime) + "'}";
	}
}
