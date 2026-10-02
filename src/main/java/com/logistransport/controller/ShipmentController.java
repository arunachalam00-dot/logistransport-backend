package com.logistransport.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;
import java.util.Optional;

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

import com.logistransport.model.Shipment;
import com.logistransport.service.ShipmentService;

@RestController
@RequestMapping("/api/shipments")
@CrossOrigin(origins = "http://localhost:5173")
@SecurityRequirement(name = "bearerAuth")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    // ADMIN: Get all shipments
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Shipment>> getAllShipments() {

        List<Shipment> shipments =
                shipmentService.getAllShipments();

        return ResponseEntity.ok(shipments);
    }

    // CUSTOMER: Track shipment using tracking number
    @GetMapping("/track/{trackingNumber}")
    public ResponseEntity<?> trackShipment(
            @PathVariable String trackingNumber) {

        Optional<Shipment> shipment =
                shipmentService.getShipmentByTrackingNumber(
                        trackingNumber
                );

        if (shipment.isPresent()) {
            return ResponseEntity.ok(shipment.get());
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Shipment not found");
    }

    // ADMIN: Get shipment by ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getShipmentById(
            @PathVariable Long id) {

        Optional<Shipment> shipment =
                shipmentService.getShipmentsById(id);

        if (shipment.isPresent()) {
            return ResponseEntity.ok(shipment.get());
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Shipment not found");
    }

    // ADMIN: Create shipment
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Shipment> createShipment(
            @RequestBody Shipment shipment) {

        Shipment savedShipment =
                shipmentService.createShipment(shipment);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedShipment);
    }

    // ADMIN: Update shipment
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateShipment(
            @PathVariable Long id,
            @RequestBody Shipment shipmentDetails) {

        Shipment updatedShipment =
                shipmentService.updateShipment(
                        id,
                        shipmentDetails
                );

        if (updatedShipment != null) {
            return ResponseEntity.ok(updatedShipment);
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Shipment not found");
    }

    // ADMIN: Delete shipment
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShipment(
            @PathVariable Long id) {

        boolean deleted =
                shipmentService.deleteShipment(id);

        if (deleted) {
            return ResponseEntity.ok(
                    "Shipment deleted successfully"
            );
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Shipment not found");
    }
}