package com.logistransport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.logistransport.model.Shipment;
import com.logistransport.model.Warehouse;
import com.logistransport.repository.ShipmentRepository;
import com.logistransport.repository.WarehouseRepository;

@Service
public class WarehouseService {
	
	private final WarehouseRepository warehouseRepository;
	private final ShipmentRepository shipmentRepository;

	public WarehouseService(WarehouseRepository warehouseRepository, ShipmentRepository shipmentRepository) {
		this.warehouseRepository = warehouseRepository;
		this.shipmentRepository = shipmentRepository;
	}
	
	public List<Warehouse> getAllWarehouses(){
		return warehouseRepository.findAll();
	}

	public Optional<Warehouse> getWarehouseById(Long id){
	    return warehouseRepository.findById(id);
	}
	
	public Optional<Warehouse> getWarehouseByShipmentId(String shipmentId){
		return warehouseRepository.findByShipment_ShipmentId(shipmentId);
	}
	
	public Warehouse createWarehouse(Warehouse warehouse) {
		
		Shipment shipment = shipmentRepository.findById(
			    warehouse.getShipment().getId()
			).orElseThrow(
			    () -> new RuntimeException("Shipment Not Found")
			);

			warehouse.setShipment(shipment);
			
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
