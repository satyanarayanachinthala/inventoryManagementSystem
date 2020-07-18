package com.cde.microprograming.orders.bo;

import java.util.Date;

public class OrdersProductBO {

	private int id;
	private int quantity;
	private Double price;
	private int productId;
	private Date createdOn;

	public OrdersProductBO() {
		super();
	}

	public OrdersProductBO(int id, int quantity, Double price, int productId, Date createdOn) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.price = price;
		this.productId = productId;
		this.createdOn = createdOn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
