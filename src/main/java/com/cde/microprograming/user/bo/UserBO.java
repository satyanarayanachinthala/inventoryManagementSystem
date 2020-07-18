package com.cde.microprograming.user.bo;

import java.util.Date;

import com.cde.microprograming.user.model.User;

public class UserBO {

	private int id;
	private String name;
	private String role;
	private int mobileNumber;
	private String email;
	private Date createdOn;

	public UserBO() {
		super();
	}

	public UserBO(int id, String name, String role, int mobileNumber, String email, Date createdOn) {
		super();
		this.id = id;
		this.name = name;
		this.role = role;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.createdOn = createdOn;
	}

	public UserBO(User userData) {
		this.id = userData.getId();
		this.name = userData.getName();
		this.role = userData.getRole();
		this.mobileNumber = userData.getMobileNumber();
		this.email = userData.getEmail();
		this.createdOn = userData.getCreatedOn();
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
