package com.cde.microprograming.product.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.cde.microprograming.product.model.Component;

public class ComponentBO implements Serializable {

	private static final long serialVersionUID = 5546027590225863326L;

	private int id;
	private int user;
	private String name;
	private int quantity;
	private int availableQuantity;
	private List<PurchasingInformationBO> purchasingInformations;
	private Date createdOn;
	private Date lastModifiedOn;

	public ComponentBO() {
		super();
	}

	public ComponentBO(int id, int user, String name, int quantity, int availableQuantity,
			List<PurchasingInformationBO> purchasingInformations, Date createdOn, Date lastModifiedOn) {
		super();
		this.id = id;
		this.user = user;
		this.name = name;
		this.quantity = quantity;
		this.availableQuantity = availableQuantity;
		this.purchasingInformations = purchasingInformations;
		this.createdOn = createdOn;
		this.lastModifiedOn = lastModifiedOn;
	}

	public ComponentBO(Component component) {
		this.id = component.getId();
		this.user = component.getUser();
		this.name = component.getName();
		this.quantity = component.getQuantity();
		this.availableQuantity = component.getAvailableQuantity();
		this.purchasingInformations = component.getPurchasingInformations().stream()
				.map(purchasingInfo -> new PurchasingInformationBO(purchasingInfo)).collect(Collectors.toList());
		this.createdOn = component.getCreatedOn();
		this.lastModifiedOn = component.getLastModifiedOn();
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
