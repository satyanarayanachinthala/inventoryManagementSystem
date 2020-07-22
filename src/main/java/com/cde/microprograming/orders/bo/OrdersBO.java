package com.cde.microprograming.orders.bo;

import java.util.Date;
import java.util.List;

import com.cde.microprograming.orders.model.Bills;
import com.cde.microprograming.orders.model.Orders;
import com.cde.microprograming.orders.model.OrdersProduct;

public class OrdersBO {

	private int id;
	private String status;
	private int productId;
	private int userId;
	private int addressId;
	private List<OrdersProduct> ordersProducts;
	private Bills bills;
	private Date createdOn;

	public OrdersBO() {
		super();
	}

	public OrdersBO(Orders orders) {
		id = orders.getId();
		status = orders.getStatus();
		productId = orders.getProductId();
		userId = orders.getUserId();
		addressId = orders.getAddressId();
		ordersProducts = orders.getOrdersProducts();
		bills = orders.getBills();
		createdOn = orders.getCreatedOn();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public List<OrdersProduct> getOrdersProducts() {
		return ordersProducts;
	}

	public void setOrdersProducts(List<OrdersProduct> ordersProducts) {
		this.ordersProducts = ordersProducts;
	}

	public Bills getBills() {
		return bills;
	}

	public void setBills(Bills bills) {
		this.bills = bills;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
