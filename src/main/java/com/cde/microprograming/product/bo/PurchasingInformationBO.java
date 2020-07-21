package com.cde.microprograming.product.bo;

import java.io.Serializable;
import java.util.Date;

import com.cde.microprograming.product.model.PurchasingInformation;

public class PurchasingInformationBO implements Serializable {

	private static final long serialVersionUID = 455082635557164905L;

	private int id;
	private int user;
	private int quantity;
	private double price;
	private String purchasedFrom;
	private Date createdOn;

	public PurchasingInformationBO() {
		super();
	}

	public PurchasingInformationBO(PurchasingInformation purchasingInformation) {
		this.id = purchasingInformation.getId();
		this.user = purchasingInformation.getUser();
	}

	public PurchasingInformationBO(int id, int user, int quantity, double price, String purchasedFrom, Date createdOn) {
		super();
		this.id = id;
		this.user = user;
		this.quantity = quantity;
		this.price = price;
		this.purchasedFrom = purchasedFrom;
		this.createdOn = createdOn;
	}

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
