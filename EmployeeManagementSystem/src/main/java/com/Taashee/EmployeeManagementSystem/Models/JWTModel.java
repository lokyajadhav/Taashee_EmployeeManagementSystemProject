package com.Taashee.EmployeeManagementSystem.Models;

public class JWTModel {
	private String username;
	private String password;
	
	public JWTModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JWTModel(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
