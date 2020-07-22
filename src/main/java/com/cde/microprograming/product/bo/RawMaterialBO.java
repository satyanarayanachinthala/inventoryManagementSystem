package com.cde.microprograming.product.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.cde.microprograming.product.model.RawMaterial;

public class RawMaterialBO implements Serializable {

	private static final long serialVersionUID = -1587845307504120840L;

	private int id;
	private int user;
	private String name;
	private String type;
	private int quantity;
	private int availableQuantity;
	private List<PurchasingInformationBO> purchasingInformations;
	private Date createdOn;
	private Date lastModifiedOn;

	public RawMaterialBO() {
		super();
	}

	public RawMaterialBO(RawMaterial rawMaterial) {
		this.id = rawMaterial.getId();
		this.user = rawMaterial.getUser();
		this.name = rawMaterial.getName();
		this.type = rawMaterial.getType();
		this.quantity = rawMaterial.getQuantity();
		this.availableQuantity = rawMaterial.getAvailableQuantity();
		this.purchasingInformations = rawMaterial.getPurchasingInformations().stream()
				.map(PurchasingInformationBO::new).collect(Collectors.toList());
		this.createdOn = rawMaterial.getCreatedOn();
		this.lastModifiedOn = rawMaterial.getLastModifiedOn();
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public List<PurchasingInformationBO> getPurchasingInformations() {
		return purchasingInformations;
	}

	public void setPurchasingInformations(List<PurchasingInformationBO> purchasingInformations) {
		this.purchasingInformations = purchasingInformations;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getLastModifiedOn() {
		return lastModifiedOn;
	}

	public void setLastModifiedOn(Date lastModifiedOn) {
		this.lastModifiedOn = lastModifiedOn;
	}

}
