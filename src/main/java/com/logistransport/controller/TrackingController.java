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

import com.logistransport.model.Tracking;
import com.logistransport.service.TrackingService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/tracking")
@CrossOrigin(origins = "http://localhost:5173")
@SecurityRequirement(name = "bearerAuth")
public class TrackingController {

	private final TrackingService trackingService;

    public TrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Tracking>> getAllTracking() {

        return ResponseEntity.ok(
                trackingService.getAllTracking()
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getTrackingById(
            @PathVariable Long id) {

        return trackingService
                .getTrackingById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(null)
                );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Tracking> createTracking(
            @RequestBody Tracking tracking) {

        Tracking savedTracking =
                trackingService.createTracking(tracking);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedTracking);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTracking(
            @PathVariable Long id,
            @RequestBody Tracking trackingDetails) {

        Tracking updatedTracking =
                trackingService.updateTracking(
                        id,
                        trackingDetails
                );

        if (updatedTracking != null) {
            return ResponseEntity.ok(updatedTracking);
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Tracking not found");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTracking(
            @PathVariable Long id) {

        boolean deleted =
                trackingService.deleteTracking(id);

        if (deleted) {
            return ResponseEntity.ok(
                    "Tracking deleted successfully"
            );
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Tracking not found");
    }	
}
