package com.Taashee.EmployeeManagementSystem.Models;

public class JWTResponse {
	private Users user;
	private String jwtToken;
	   private String errorMessage;
	   
	   
	public JWTResponse(Users user, String jwtToken, String errorMessage) {
		super();
		this.user = user;
		this.jwtToken = jwtToken;
		this.errorMessage = errorMessage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	public JWTResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JWTResponse(Users user, String jwtToken) {
		super();
		this.user = user;
		this.jwtToken = jwtToken;
	}
	
	

}
