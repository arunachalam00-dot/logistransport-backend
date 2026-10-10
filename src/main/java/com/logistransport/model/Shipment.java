package com.logistransport.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "shipments")
public class Shipment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String trackingNumber;
	
	@Column(name ="shipment_id",nullable = false, unique = true)
	private String shipmentId;
	
	@Column(nullable = false)
	private String senderName;
	
	@Column(nullable = false)
	private String receiverName;
	
	@Column(nullable = false)
	private String source;
	
	@Column(nullable = false)
	private String destination;
	
	@Column(nullable = false)
	private String status;
	
	public Shipment() {
		
	}

	public Shipment(Long id, String trackingNumber, String shipmentId , String senderName, String receiverName, String source,
			String destination, String status) {
		super();
		this.id = id;
		this.trackingNumber = trackingNumber;
		this.shipmentId= shipmentId;
		this.senderName = senderName;
		this.receiverName = receiverName;
		this.source = source;
		this.destination = destination;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getShipmentId() {
		return shipmentId;
	}
	
	public void setShipmentId(String shipmentId ) {
		this.shipmentId = shipmentId;
	}
	
	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
