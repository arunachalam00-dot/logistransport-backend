package com.logistransport.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "warehouses")
public class Warehouse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String shipmentId;
	
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

	public Warehouse(Long id,String shipmentId, String warehouseName, String location, String managerName, String contactNumber) {
		super();
		this.id = id;
		this.shipmentId = shipmentId;
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

	public String getShipmentId() {
	    return shipmentId;
	}

	public void setShipmentId(String shipmentId) {
	    this.shipmentId = shipmentId;
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
