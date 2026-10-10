package com.logistransport.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistransport.model.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
	
	Optional<Warehouse> findByShipment_ShipmentId(String shipmentId);

}
