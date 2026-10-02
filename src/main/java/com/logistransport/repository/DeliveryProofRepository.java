package com.logistransport.repository;

import com.logistransport.model.DeliveryProof;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryProofRepository
        extends JpaRepository<DeliveryProof, Long> {
}