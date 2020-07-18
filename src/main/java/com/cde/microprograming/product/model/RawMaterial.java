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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.cde.microprograming.product.bo.RawMaterialBO;

@Entity
@Table(name = "raw_material")
public class RawMaterial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int user;

	private String name;

	private String type;

	private int quantity;

	@Column(name = "available_quantity")
	private int availableQuantity;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "raw_material_id")
	private List<PurchasingInformation> purchasingInformations;

	@Column(name = "created_on")
	@CreationTimestamp
	private Date createdOn;

	@Column(name = "last_modified_on")
	@UpdateTimestamp
	private Date lastModifiedOn;

	public RawMaterial() {
		super();
	}

	public RawMaterial(RawMaterialBO rawMaterialBO) {
		this.id = rawMaterialBO.getId();
		this.user = rawMaterialBO.getUser();
		this.name = rawMaterialBO.getName();
		this.type = rawMaterialBO.getType();
		this.quantity = rawMaterialBO.getQuantity();
		this.availableQuantity = rawMaterialBO.getAvailableQuantity();
		this.createdOn = rawMaterialBO.getCreatedOn();
		this.lastModifiedOn = rawMaterialBO.getLastModifiedOn();
		this.purchasingInformations = rawMaterialBO.getPurchasingInformations().stream()
				.map(purchasingInfo -> new PurchasingInformation(purchasingInfo)).collect(Collectors.toList());
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
