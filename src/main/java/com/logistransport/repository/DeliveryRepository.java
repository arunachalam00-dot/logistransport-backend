package com.logistransport.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistransport.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long>{
	Optional<Delivery> findByShipment_ShipmentId(String shipmentId);

}
