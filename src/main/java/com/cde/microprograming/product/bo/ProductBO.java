package com.cde.microprograming.product.bo;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.cde.microprograming.product.model.Product;
import com.cde.microprograming.product.model.ProductInventory;

public class ProductBO {

	private int id;
	private String name;
	private String status;
	private int quantity;
	private String type;
	private int componentId;
	private int rawMaterialId;
	private List<ProductInventory> productInventories;
	private Date createdOn;

	public ProductBO() {
		super();
	}

	public ProductBO(int id, String name, String status, int quantity, String type, int componentId, int rawMaterialId,
			List<ProductInventory> productInventories, Date createdOn) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.quantity = quantity;
		this.type = type;
		this.componentId = componentId;
		this.rawMaterialId = rawMaterialId;
		this.productInventories = productInventories;
		this.createdOn = createdOn;
	}

	public ProductBO(Product product) {
		id = product.getId();
		name = product.getName();
		status = product.getStatus();
		quantity = product.getQuantity();
		type = product.getType();
		componentId = product.getComponentId();
		rawMaterialId = product.getRawMaterialId();
		productInventories = product.getProductInventories().stream()
				.map(productInfo -> new ProductInventory(productInfo)).collect(Collectors.toList());
		;
		createdOn = product.getCreatedOn();
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
