package com.logistransport.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

import com.logistransport.model.Warehouse;
import com.logistransport.service.WarehouseService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/warehouses")
@CrossOrigin(origins= "http://localhost:5173")
@SecurityRequirement(name = "bearerAuth")
public class WarehouseController {
	
	private final WarehouseService warehouseService;

	public WarehouseController(WarehouseService warehouseService) {
		this.warehouseService = warehouseService;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<Warehouse>> getAllWarehouses() {
		
		return ResponseEntity.ok(warehouseService.getAllWarehouses());
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<?> getWarehouseById(@PathVariable Long id){
		
		return warehouseService.getWarehouseById(id).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
		
		Warehouse savedWarehouse = warehouseService.createWarehouse(warehouse);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedWarehouse);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<?> updateWarehouse(@PathVariable Long id, @RequestBody Warehouse warehouseDetails){
		
		Warehouse updatedWarehouse = warehouseService.updateWarehouse(id, warehouseDetails);
		
		if (updatedWarehouse != null) {
			return ResponseEntity.ok(updatedWarehouse);
	}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Warehouse not found");
		
   }
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteWarehouse(@PathVariable Long id){
		boolean deleted = warehouseService.deleteWarehouse(id);
		
		if (deleted) {
			return ResponseEntity.ok("Warehouse deleted");
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Warehouse not found");
	}
}
