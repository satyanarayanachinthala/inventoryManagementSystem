package com.cde.microprograming.product.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cde.microprograming.product.bo.PurchasingInformationBO;

@Entity
@Table(name = "purchasing_information")
public class PurchasingInformation {

	public PurchasingInformation() {
		super();
	}

	public PurchasingInformation(PurchasingInformationBO purchasingInformation) {
		id = purchasingInformation.getId();
		user = purchasingInformation.getUser();
		quantity = purchasingInformation.getQuantity();
		price = purchasingInformation.getPrice();
		purchasedFrom = purchasingInformation.getPurchasedFrom();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int user;

	private int quantity;

	private double price;

	@Column(name = "purchaed_from")
	private String purchasedFrom;

	@Column(name = "created_on")
	private Date createdOn;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPurchasedFrom() {
		return purchasedFrom;
	}

	public void setPurchasedFrom(String purchasedFrom) {
		this.purchasedFrom = purchasedFrom;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
