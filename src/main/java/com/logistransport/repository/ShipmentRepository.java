package com.logistransport.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistransport.model.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

	List<Shipment> findByStatus(String status);
	
	Optional<Shipment> findByTrackingNumber(String trackingNumber);
}
