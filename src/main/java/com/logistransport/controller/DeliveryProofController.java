package com.logistransport.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistransport.model.DeliveryProof;
import com.logistransport.service.DeliveryProofService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/delivery-proofs")
@CrossOrigin(origins = "http://localhost:5173")
@SecurityRequirement(name = "bearerAuth")
@PreAuthorize("hasRole('ADMIN')")
public class DeliveryProofController {

    private final DeliveryProofService deliveryProofService;

    public DeliveryProofController(
            DeliveryProofService deliveryProofService) {

        this.deliveryProofService = deliveryProofService;
    }

    @GetMapping
    public ResponseEntity<List<DeliveryProof>>
            getAllDeliveryProofs() {

        return ResponseEntity.ok(
                deliveryProofService.getAllDeliveryProofs()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDeliveryProofById(
            @PathVariable Long id) {

        return deliveryProofService
                .getDeliveryProofById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(null)
                );
    }

    @PostMapping
    public ResponseEntity<DeliveryProof> createDeliveryProof(
            @RequestBody DeliveryProof deliveryProof) {

        DeliveryProof savedDeliveryProof =
                deliveryProofService.createDeliveryProof(
                        deliveryProof
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedDeliveryProof);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDeliveryProof(
            @PathVariable Long id,
            @RequestBody DeliveryProof deliveryProofDetails) {

        DeliveryProof updatedDeliveryProof =
                deliveryProofService.updateDeliveryProof(
                        id,
                        deliveryProofDetails
                );

        if (updatedDeliveryProof != null) {
            return ResponseEntity.ok(updatedDeliveryProof);
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Delivery proof not found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDeliveryProof(
            @PathVariable Long id) {

        boolean deleted =
                deliveryProofService.deleteDeliveryProof(id);

        if (deleted) {
            return ResponseEntity.ok(
                    "Delivery proof deleted successfully"
            );
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Delivery proof not found");
    }
}