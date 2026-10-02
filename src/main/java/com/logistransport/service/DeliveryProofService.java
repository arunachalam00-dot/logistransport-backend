package com.logistransport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.logistransport.model.DeliveryProof;
import com.logistransport.repository.DeliveryProofRepository;

@Service
public class DeliveryProofService {

    private final DeliveryProofRepository deliveryProofRepository;

    public DeliveryProofService(
            DeliveryProofRepository deliveryProofRepository) {

        this.deliveryProofRepository = deliveryProofRepository;
    }

    public List<DeliveryProof> getAllDeliveryProofs() {
        return deliveryProofRepository.findAll();
    }

    public Optional<DeliveryProof> getDeliveryProofById(Long id) {
        return deliveryProofRepository.findById(id);
    }

    public DeliveryProof createDeliveryProof(
            DeliveryProof deliveryProof) {

        return deliveryProofRepository.save(deliveryProof);
    }

    public DeliveryProof updateDeliveryProof(
            Long id,
            DeliveryProof deliveryProofDetails) {

        Optional<DeliveryProof> optionalDeliveryProof =
                deliveryProofRepository.findById(id);

        if (optionalDeliveryProof.isPresent()) {

            DeliveryProof deliveryProof =
                    optionalDeliveryProof.get();

            deliveryProof.setShipmentId(
                    deliveryProofDetails.getShipmentId()
            );

            deliveryProof.setReceiverName(
                    deliveryProofDetails.getReceiverName()
            );

            deliveryProof.setDeliveryDate(
                    deliveryProofDetails.getDeliveryDate()
            );

            deliveryProof.setDeliveryStatus(
                    deliveryProofDetails.getDeliveryStatus()
            );

            deliveryProof.setProofFileName(
                    deliveryProofDetails.getProofFileName()
            );

            return deliveryProofRepository.save(deliveryProof);
        }

        return null;
    }

    public boolean deleteDeliveryProof(Long id) {

        Optional<DeliveryProof> deliveryProof =
                deliveryProofRepository.findById(id);

        if (deliveryProof.isPresent()) {

            deliveryProofRepository.delete(
                    deliveryProof.get()
            );

            deliveryProofRepository.flush();

            return true;
        }

        return false;
    }
}