package com.logistransport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.logistransport.model.Delivery;
import com.logistransport.repository.DeliveryRepository;

@Service
public class DeliveryService {

	private final DeliveryRepository deliveryRepository;

	public DeliveryService(DeliveryRepository deliveryRepository) {
		this.deliveryRepository = deliveryRepository;
	}
	
	public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }
	
	public Optional<Delivery> getDeliveryById(Long id) {
        return deliveryRepository.findById(id);
    }
	
	public Delivery createDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }
	
	public Delivery updateDelivery(
            Long id,
            Delivery deliveryDetails) {

        Optional<Delivery> optionalDelivery =
                deliveryRepository.findById(id);

        if (optionalDelivery.isPresent()) {

            Delivery delivery =
                    optionalDelivery.get();

            delivery.setShipmentId(
                    deliveryDetails.getShipmentId()
            );

            delivery.setDeliveryPerson(
                    deliveryDetails.getDeliveryPerson()
            );

            delivery.setDeliveryDate(
                    deliveryDetails.getDeliveryDate()
            );

            delivery.setStatus(
                    deliveryDetails.getStatus()
            );

            return deliveryRepository.save(delivery);
        }
          return null;
}
	
	public boolean deleteDelivery(Long id) {

        Optional<Delivery> delivery =
                deliveryRepository.findById(id);

        if (delivery.isPresent()) {

            deliveryRepository.delete(delivery.get());

            deliveryRepository.flush();

            return true;
        }

        return false;
    }
}
