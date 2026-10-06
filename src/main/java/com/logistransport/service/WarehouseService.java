package com.logistransport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.logistransport.model.Warehouse;
import com.logistransport.repository.WarehouseRepository;

@Service
public class WarehouseService {
	
	public final WarehouseRepository warehouseRepository;

	public WarehouseService(WarehouseRepository warehouseRepository) {
		super();
		this.warehouseRepository = warehouseRepository;
	}
	
	public List<Warehouse> getAllWarehouses(){
		return warehouseRepository.findAll();
	}

	public Optional<Warehouse> getWarehouseById(Long id){
	    return warehouseRepository.findById(id);
	}
	
	public Optional<Warehouse> getWarehouseByShipmentId(String shipmentId){
		return warehouseRepository.findByShipmentId(shipmentId);
	}
	
	public Warehouse createWarehouse(Warehouse warehouse) {
		return warehouseRepository.save(warehouse);
	}
	
	public Warehouse updateWarehouse(Long id, Warehouse warehouseDetails) {
		
		Optional<Warehouse> optionalWarehouse= warehouseRepository.findById(id);
		
		if (optionalWarehouse.isPresent()) {
			
			Warehouse warehouse=optionalWarehouse.get();
			
			warehouse.setWarehouseName(warehouseDetails.getWarehouseName());
			
			warehouse.setLocation(warehouseDetails.getLocation());
			
			warehouse.setManagerName(warehouseDetails.getManagerName());
			
			warehouse.setContactNumber(warehouseDetails.getContactNumber());
			
			return warehouseRepository.save(warehouse);
		}
		
		return null;
	}
	
	public boolean deleteWarehouse(Long id) {
		Optional<Warehouse> warehouse= warehouseRepository.findById(id);
		
		if (warehouse.isPresent()) {
			
			warehouseRepository.delete(warehouse.get());
			
			warehouseRepository.flush();
			
			return true;
		}
		
		return false;
	}
	
}
