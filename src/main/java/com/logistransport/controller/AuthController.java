package com.logistransport.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistransport.dto.LoginRequest;
import com.logistransport.dto.LoginResponse;
import com.logistransport.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService=authService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
		
		LoginResponse response=authService.login(request);
		
		return ResponseEntity.ok(response);
	}
}
