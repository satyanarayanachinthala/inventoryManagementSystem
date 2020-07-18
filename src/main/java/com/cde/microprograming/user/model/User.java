package com.cde.microprograming.user.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cde.microprograming.user.bo.UserBO;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "role")
	private String role;

	@Column(name = "mobile_number")
	private int mobileNumber;

	@Column(name = "email")
	private String email;

	@Column(name = "created_on")
	private Date createdOn;

	public User() {
		super();
	}

	public User(UserBO userBO) {
		this.id = userBO.getId();
		this.name = userBO.getName();
		this.role = userBO.getRole();
		this.mobileNumber = userBO.getMobileNumber();
		this.email = userBO.getEmail();
		this.createdOn = userBO.getCreatedOn();
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
