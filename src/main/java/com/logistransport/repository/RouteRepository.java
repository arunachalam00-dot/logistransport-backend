package com.logistransport.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistransport.model.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {
	
	Optional<Route> findByShipmentId(String shipmentId);

}
