package com.logistransport.dto;

public class LoginRequest {
	
	private String email;
	
	private String Password;
	
	private String role;

	//Spring needs this to create the object from JSON
	public LoginRequest() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
