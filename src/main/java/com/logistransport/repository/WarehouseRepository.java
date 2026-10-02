package com.logistransport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistransport.model.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

}
