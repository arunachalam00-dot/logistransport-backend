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

import com.logistransport.model.Route;
import com.logistransport.service.RouteService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/routes")
@CrossOrigin(origins = "http://localhost:5173")
@SecurityRequirement(name = "bearerAuth")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Route>> getAllRoutes() {

        return ResponseEntity.ok(
                routeService.getAllRoutes()
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getRouteById(
            @PathVariable Long id) {

        return routeService
                .getRouteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(null)
                );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Route> createRoute(
            @RequestBody Route route) {

        Route savedRoute =
                routeService.createRoute(route);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedRoute);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRoute(
            @PathVariable Long id,
            @RequestBody Route routeDetails) {

        Route updatedRoute =
                routeService.updateRoute(
                        id,
                        routeDetails
                );

        if (updatedRoute != null) {
            return ResponseEntity.ok(updatedRoute);
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Route not found");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoute(
            @PathVariable Long id) {

        boolean deleted =
                routeService.deleteRoute(id);

        if (deleted) {
            return ResponseEntity.ok(
                    "Route deleted successfully"
            );
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Route not found");
    }
}
