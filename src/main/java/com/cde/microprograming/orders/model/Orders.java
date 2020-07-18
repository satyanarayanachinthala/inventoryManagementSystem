package com.cde.microprograming.orders.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cde.microprograming.orders.bo.OrdersBO;

@Entity
@Table(name = "orders")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "status")
	private String status;

	@Column(name = "product_id")
	private int productId;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "address_id")
	private int addressId;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "order_id")
	private List<OrdersProduct> ordersProducts;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "order_id")
	private Bills bills;

	@Column(name = "created_on")
	private Date createdOn;

	public Orders() {
		super();
	}

	public Orders(OrdersBO ordersBO) {
		this.id = ordersBO.getId();
		this.status = ordersBO.getStatus();
		this.productId = ordersBO.getProductId();
		this.userId = ordersBO.getUserId();
		this.addressId = ordersBO.getAddressId();
		this.ordersProducts = ordersBO.getOrdersProducts();
		this.bills = ordersBO.getBills();
		this.createdOn = ordersBO.getCreatedOn();
	}

	public Bills getBills() {
		return bills;
	}

	public void setBills(Bills bills) {
		this.bills = bills;
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

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
