package com.logistransport.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

import com.logistransport.model.Delivery;
import com.logistransport.model.Shipment;
import com.logistransport.model.Warehouse;
import com.logistransport.repository.DeliveryRepository;
import com.logistransport.repository.WarehouseRepository;
import com.logistransport.service.ShipmentService;

@RestController
@RequestMapping("/api/shipments")
@CrossOrigin(origins = "http://localhost:5173")
@SecurityRequirement(name = "bearerAuth")
public class ShipmentController {

    private final ShipmentService shipmentService;
    private final WarehouseRepository warehouseRepository;
    private final DeliveryRepository deliveryRepository;

    public ShipmentController(ShipmentService shipmentService,
    		WarehouseRepository warehouseRepository,
            DeliveryRepository deliveryRepository) {
        this.shipmentService = shipmentService;
        this.warehouseRepository = warehouseRepository;
        this.deliveryRepository = deliveryRepository;
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

 // ADMIN: Search shipment, warehouse and delivery together
 @PreAuthorize("hasRole('ADMIN')")
 @GetMapping("/search/{shipmentId}")
 public ResponseEntity<?> searchShipmentDetails(
         @PathVariable String shipmentId) {

     Optional<Shipment> shipmentOptional =
             shipmentService.getShipmentByShipmentId(shipmentId);

     if (shipmentOptional.isEmpty()) {
         return ResponseEntity
                 .status(HttpStatus.NOT_FOUND)
                 .body("Shipment ID not found");
     }

     Shipment shipment = shipmentOptional.get();

     Optional<Warehouse> warehouseOptional =
             warehouseRepository.findByShipment_ShipmentId(shipmentId);

     Optional<Delivery> deliveryOptional =
             deliveryRepository.findByShipment_ShipmentId(shipmentId);

     Map<String, Object> shipmentDetails = new LinkedHashMap<>();
     shipmentDetails.put("shipmentId", shipment.getShipmentId());
     shipmentDetails.put("trackingNumber", shipment.getTrackingNumber());
     shipmentDetails.put("senderName", shipment.getSenderName());
     shipmentDetails.put("receiverName", shipment.getReceiverName());
     shipmentDetails.put("source", shipment.getSource());
     shipmentDetails.put("destination", shipment.getDestination());
     shipmentDetails.put("status", shipment.getStatus());

     Map<String, Object> warehouseDetails = null;

     if (warehouseOptional.isPresent()) {
         Warehouse warehouse = warehouseOptional.get();

         warehouseDetails = new LinkedHashMap<>();
         warehouseDetails.put("warehouseName", warehouse.getWarehouseName());
         warehouseDetails.put("location", warehouse.getLocation());
         warehouseDetails.put("managerName", warehouse.getManagerName());
         warehouseDetails.put("contactNumber", warehouse.getContactNumber());
     }

     Map<String, Object> deliveryDetails = null;

     if (deliveryOptional.isPresent()) {
         Delivery delivery = deliveryOptional.get();

         deliveryDetails = new LinkedHashMap<>();
         deliveryDetails.put("deliveryPerson", delivery.getDeliveryPerson());
         deliveryDetails.put("deliveryDate", delivery.getDeliveryDate());
         deliveryDetails.put("status", delivery.getStatus());
     }

     Map<String, Object> result = new LinkedHashMap<>();
     result.put("shipment", shipmentDetails);
     result.put("warehouse", warehouseDetails);
     result.put("delivery", deliveryDetails);

     return ResponseEntity.ok(result);
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