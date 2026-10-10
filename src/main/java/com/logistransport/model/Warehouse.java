package com.logistransport.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "warehouses")
public class Warehouse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "shipment_id" , referencedColumnName = "shipment_id" , nullable = false)
	private Shipment shipment;
	
	@Column(nullable = false)
	private String warehouseName;
	
	@Column(nullable = false)
	private String location;
	
	@Column(nullable = false)
	private String managerName;
	
	@Column(nullable = false)
	private String  contactNumber;
	
	public Warehouse() {
		
	}

	public Warehouse(Long id,Shipment shipment, String warehouseName, String location, String managerName, String contactNumber) {
		super();
		this.id = id;
		this.shipment = shipment;
		this.warehouseName = warehouseName;
		this.location = location;
		this.managerName = managerName;
		this.contactNumber = contactNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Shipment getShipment() {
	    return shipment;
	}

	public void setShipment(Shipment shipment) {
	    this.shipment = shipment;
	}
	
	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	
}
