package com.cde.microprograming.product.model;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cde.microprograming.product.bo.ProductBO;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "status")
	private String status;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "type")
	private String type;

	@Column(name = "component_id")
	private int componentId;

	@Column(name = "raw_material_id")
	private int rawMaterialId;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "product_id")
	private List<ProductInventory> productInventories;

	@Column(name = "created_on")
	private Date createdOn;

	public Product() {
		super();
	}

	public Product(ProductBO productBO) {
		id = productBO.getId();
		name = productBO.getName();
		status = productBO.getStatus();
		quantity = productBO.getQuantity();
		type = productBO.getType();
		componentId = productBO.getComponentId();
		rawMaterialId = productBO.getRawMaterialId();
		this.productInventories = productBO.getProductInventories().stream()
				.map(ProductInventory::new).collect(Collectors.toList());
		createdOn = productBO.getCreatedOn();
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getComponentId() {
		return componentId;
	}

	public void setComponentId(int componentId) {
		this.componentId = componentId;
	}

	public int getRawMaterialId() {
		return rawMaterialId;
	}

	public void setRawMaterialId(int rawMaterialId) {
		this.rawMaterialId = rawMaterialId;
	}

	public List<ProductInventory> getProductInventories() {
		return productInventories;
	}

	public void setProductInventories(List<ProductInventory> productInventories) {
		this.productInventories = productInventories;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
