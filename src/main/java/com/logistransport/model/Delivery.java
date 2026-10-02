package com.logistransport.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "deliveries")
public class Delivery {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
    private String shipmentId;
	
	@Column(nullable = false)
    private String deliveryPerson;
	
	@Column(nullable = false)
	private String deliveryDate;
	
	@Column(nullable = false)
    private String status;
	
	public Delivery() {
		
	}

	public Delivery(Long id, String shipmentId, String deliveryPerson,String deliveryDate, String status) {
		this.id = id;
		this.shipmentId = shipmentId;
		this.deliveryPerson = deliveryPerson;
		this.deliveryDate = deliveryDate;
		this.status = status;
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

	public String getDeliveryPerson() {
		return deliveryPerson;
	}

	public void setDeliveryPerson(String deliveryPerson) {
		this.deliveryPerson = deliveryPerson;
	}
	
	public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
