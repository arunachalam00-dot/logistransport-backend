package com.logistransport.service;

import com.logistransport.model.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.logistransport.dto.LoginRequest;
import com.logistransport.dto.LoginResponse;
import com.logistransport.model.User;
import com.logistransport.repository.UserRepository;
import com.logistransport.security.JwtService;

@Service                         
public class AuthService {
	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JwtService jwtService;
	
	public AuthService(UserRepository userRepository , PasswordEncoder passwordEncoder , JwtService jwtService) {
		
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService=jwtService;
	}

	public LoginResponse login(LoginRequest request) {
		
		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(()->new RuntimeException("Invalid email or password"));
		
		if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid Email or Password");
		}
		
//		Role requestedRole;
//		
//		try {
//			requestedRole= Role.valueOf(request.getRole().toUpperCase());
//		}
//		
//		catch(Exception e) {
//			throw new RuntimeException("Invalid Role");
//		}
//		
//		if(user.getRole() != requestedRole) {
//			throw new RuntimeException("Role does not Match");
//		}
		
		String token = jwtService.generateToken(user.getEmail(), user.getRole().name());
		
		return new LoginResponse("Login Succesful", user.getEmail(), user.getRole().name(), token);
		

	}
}
