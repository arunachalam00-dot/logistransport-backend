package com.logistransport.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistransport.repository.DeliveryProofRepository;
import com.logistransport.repository.DeliveryRepository;
import com.logistransport.repository.RouteRepository;
import com.logistransport.repository.ShipmentRepository;
import com.logistransport.repository.TrackingRepository;
import com.logistransport.repository.WarehouseRepository;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "http://localhost:5173")
public class ReportController {

    private final ShipmentRepository shipmentRepository;
    private final WarehouseRepository warehouseRepository;
    private final DeliveryRepository deliveryRepository;
    private final TrackingRepository trackingRepository;
    private final RouteRepository routeRepository;
    private final DeliveryProofRepository deliveryProofRepository;

    public ReportController(
            ShipmentRepository shipmentRepository,
            WarehouseRepository warehouseRepository,
            DeliveryRepository deliveryRepository,
            TrackingRepository trackingRepository,
            RouteRepository routeRepository,
            DeliveryProofRepository deliveryProofRepository) {

        this.shipmentRepository = shipmentRepository;
        this.warehouseRepository = warehouseRepository;
        this.deliveryRepository = deliveryRepository;
        this.trackingRepository = trackingRepository;
        this.routeRepository = routeRepository;
        this.deliveryProofRepository = deliveryProofRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/summary")
    public ResponseEntity<Map<String, Long>> getSummaryReport() {

        Map<String, Long> report = new HashMap<>();
        
        long totalShipments = shipmentRepository.count();
        
        report.put( "totalShipments", shipmentRepository.count() );
        
        report.put( "totalWarehouses", warehouseRepository.count() ); 
        
        report.put( "totalDeliveries", deliveryRepository.count() ); 
        
        report.put( "totalTrackingRecords", trackingRepository.count() ); 
        
        report.put( "totalRoutes", routeRepository.count() );
        
        report.put( "totalDeliveryProofs", deliveryProofRepository.count() );


        return ResponseEntity.ok(report);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/shipment-status") 
    public ResponseEntity<Map<String, Long>> getShipmentStatusReport() { 
    	Map<String, Long> report = new HashMap<>();
    	
    	 report.put(
                 "pending",
                 (long) shipmentRepository
                         .findByStatus("Pending")
                         .size()
         );

         report.put(
                 "pickedUp",
                 (long) shipmentRepository
                         .findByStatus("Picked Up")
                         .size()
         );

         report.put(
                 "inTransit",
                 (long) shipmentRepository
                         .findByStatus("In Transit")
                         .size()
         );

         report.put(
                 "outForDelivery",
                 (long) shipmentRepository
                         .findByStatus("Out for Delivery")
                         .size()
         );
         
         report.put(
        		 "delivered",
        		 (long) shipmentRepository
        		 .findByStatus("Delivered")
        		 .size()
        		 );
         
         return ResponseEntity.ok(report);
    }
}