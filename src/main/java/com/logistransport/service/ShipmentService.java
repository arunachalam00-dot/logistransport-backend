package com.logistransport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.logistransport.model.Shipment;
import com.logistransport.repository.ShipmentRepository;

@Service
public class ShipmentService {
	
	private final ShipmentRepository shipmentRepository;
	
	public ShipmentService(ShipmentRepository shipmentRepository) {
		this.shipmentRepository=shipmentRepository;
	}
	
	//getAll
	public List<Shipment> getAllShipments(){
		return shipmentRepository.findAll();
	}

	//Get bY ID
	public Optional<Shipment> getShipmentsById(Long id){
		return shipmentRepository.findById(id);
	}
	
    // Get shipment by tracking number
    public Optional<Shipment> getShipmentByTrackingNumber(
            String trackingNumber) {

        return shipmentRepository
                .findByTrackingNumber(trackingNumber);
    }
	
	//Create Shipment
	public Shipment createShipment(Shipment shipment) {
		return shipmentRepository.save(shipment);
	}
	
	
	//Update
	public Shipment updateShipment(Long id, Shipment shipmentDetails) {
		
		Optional<Shipment> optionalShipment=shipmentRepository.findById(id);
		
		if(optionalShipment.isPresent()) {
			Shipment shipment=optionalShipment.get();
			
			shipment.setTrackingNumber(shipmentDetails.getTrackingNumber());
			
			shipment.setSenderName(shipmentDetails.getSenderName());
			
			shipment.setReceiverName(shipmentDetails.getReceiverName());
			
			shipment.setSource(shipmentDetails.getSource());
			
			shipment.setDestination(shipmentDetails.getDestination());
			
			shipment.setStatus(shipmentDetails.getStatus());
			
			return shipmentRepository.save(shipment);
		}
		return null;
	}
	
	//delete
	public boolean deleteShipment(Long id) {
		
		Optional<Shipment> shipment = shipmentRepository.findById(id);
		
		if (shipment.isPresent()) {
			
			shipmentRepository.delete(shipment.get());
			
			shipmentRepository.flush();
			
			return true;
		}
		return false;
	}
}
