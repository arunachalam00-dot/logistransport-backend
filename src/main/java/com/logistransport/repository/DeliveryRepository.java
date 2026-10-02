package com.logistransport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistransport.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long>{

}
