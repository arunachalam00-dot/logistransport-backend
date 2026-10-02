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

import com.logistransport.model.Delivery;
import com.logistransport.service.DeliveryService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/deliveries")
@CrossOrigin(origins = "http://localhost:5173")
@SecurityRequirement(name = "Bearer Auth")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Delivery>> getAllDeliveries() {

        return ResponseEntity.ok(
                deliveryService.getAllDeliveries()
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getDeliveryById(
            @PathVariable Long id) {

        return deliveryService
                .getDeliveryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(null)
                );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Delivery> createDelivery(
            @RequestBody Delivery delivery) {

    	System.out.println("Create called ");
    	
        Delivery savedDelivery =
                deliveryService.createDelivery(delivery);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedDelivery);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDelivery(
            @PathVariable Long id,
            @RequestBody Delivery deliveryDetails) {

        Delivery updatedDelivery =
                deliveryService.updateDelivery(
                        id,
                        deliveryDetails
                );

        if (updatedDelivery != null) {

            return ResponseEntity.ok(updatedDelivery);
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Delivery not found");
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDelivery(
            @PathVariable Long id) {

        boolean deleted =
                deliveryService.deleteDelivery(id);

        if (deleted) {

            return ResponseEntity.ok(
                    "Delivery deleted successfully"
            );
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Delivery not found");
    }

}
