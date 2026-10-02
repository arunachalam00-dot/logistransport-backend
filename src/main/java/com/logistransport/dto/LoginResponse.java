package com.logistransport.dto;

public class LoginResponse {

	private String message;
	
	private String email;
	
	private String role;
	
	private String token;
	
	public LoginResponse(
			String message,
			String email,
			String role,
			String token) {
		
		this.message = message;
		this.email = email;
		this.role = role;
		this.token=token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		
	}
	
	public String getToken() {
		return token;
	}
}
