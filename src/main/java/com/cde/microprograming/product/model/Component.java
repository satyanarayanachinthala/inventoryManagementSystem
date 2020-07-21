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

import com.cde.microprograming.product.bo.ComponentBO;

@Entity
@Table(name = "component")
public class Component {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int user;

	private String name;

	private int quantity;

	@Column(name = "available_quantity")
	private int availableQuantity;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "component_id")
	private List<PurchasingInformation> purchasingInformations;

	@Column(name = "created_on")
	private Date createdOn;

	@Column(name = "last_modified_on")
	private Date lastModifiedOn;

	public Component() {
		super();
	}

	public Component(ComponentBO componentBO) {
		this.id = componentBO.getId();
		this.user = componentBO.getUser();
		this.name = componentBO.getName();
		this.quantity = componentBO.getQuantity();
		this.availableQuantity = componentBO.getAvailableQuantity();
		this.purchasingInformations = componentBO.getPurchasingInformations().stream()
				.map(PurchasingInformation::new).collect(Collectors.toList());
		this.createdOn = componentBO.getCreatedOn();
		this.lastModifiedOn = componentBO.getLastModifiedOn();
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

	public List<PurchasingInformation> getPurchasingInformations() {
		return purchasingInformations;
	}

	public void setPurchasingInformations(List<PurchasingInformation> purchasingInformations) {
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
