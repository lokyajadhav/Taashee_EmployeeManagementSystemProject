package com.Taashee.EmployeeManagementSystem.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Taashee.EmployeeManagementSystem.Models.Employee;
import com.Taashee.EmployeeManagementSystem.Models.JWTModel;
import com.Taashee.EmployeeManagementSystem.Models.JWTResponse;
import com.Taashee.EmployeeManagementSystem.Models.Users;
import com.Taashee.EmployeeManagementSystem.Security.DaoUserDetailsService;
import com.Taashee.EmployeeManagementSystem.Security.TokenGeneration;
import com.Taashee.EmployeeManagementSystem.Services.ManagementServices;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RootController {
	
	@Autowired
	private ManagementServices managementServices;
	@Autowired
	private TokenGeneration tokenGeneration;
	@Autowired
	private DaoUserDetailsService daoUserDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@GetMapping("/getEmployees")
	public List<Employee> getEmployees()
	{
		return managementServices.getEmployee();
	}
	@PostMapping("/addEmployees")
	public List<Employee> addEmployee(@RequestBody Employee employee)
	{
		return managementServices.addEmployee(employee);
	}
	@DeleteMapping("/deleteEmployee/{employee_id}")
	public List<Employee> deleteEmployee(@PathVariable String employee_id)
	{
		return managementServices.deleteEmployee(employee_id);
	}
	@GetMapping("/getEmployeeDetails/{employee_id}")
	public Employee getEmployee(@PathVariable String employee_id)
	{
		System.out.println(employee_id);
		return managementServices.getEmployee(employee_id);
	}
	@PutMapping("/updateEmployee")
	public List<Employee> updateEmployee(@RequestBody Employee employee)
	{
		return managementServices.updateEmployee(employee);
	}
	@PostMapping("/authenticate")
	public ResponseEntity<JWTResponse> login(@RequestBody JWTModel jwtRequest)
	{
		return tokenGeneration.createToken(jwtRequest);
	}
	@PostMapping("/register")
	public int register(@RequestBody Users user)
	{
		user.setRole("USER");
		String EncPassword=passwordEncoder.encode(user.getPassword());
		user.setPassword(EncPassword);
		return managementServices.register(user);
	}
	
}
