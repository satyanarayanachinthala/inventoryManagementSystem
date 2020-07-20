package com.cde.microprograming.user.bo;

import com.cde.microprograming.user.model.User;

public class UserBO {

	private int id;
	private String userName;
	private String password;
	private String mobileNumber;
	private int age;

	public UserBO() {
		super();
	}

	public UserBO(int id, String userName, String password, String mobileNumber, int age) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.age = age;
	}

	public UserBO(User userData) {
		id = userData.getId();
		userName = userData.getUserName();
		password = userData.getPassword();
		mobileNumber = userData.getMobileNumber();
		age = userData.getAge();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
