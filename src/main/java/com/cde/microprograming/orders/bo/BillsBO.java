package com.cde.microprograming.orders.bo;

import java.util.Date;

public class BillsBO {

	private int id;
	private Double amount;
	private String paymentMethod;
	private Date createdOn;

	public BillsBO() {
		super();
	}

	public BillsBO(int id, Double amount, String paymentMethod, Date createdOn) {
		super();
		this.id = id;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.createdOn = createdOn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
