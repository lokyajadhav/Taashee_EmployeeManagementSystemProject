package com.Taashee.EmployeeManagementSystem.Dao;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.Taashee.EmployeeManagementSystem.Models.Employee;
import com.Taashee.EmployeeManagementSystem.Models.Users;

public interface ManagementDao {

	List<Employee> getEmployees();

	List<Employee> addEmployee(Employee employee);

	List<Employee> deleteEmployee(String employee_id);

	Employee getEmployee(String employee_id);

	List<Employee> updateEmployee(Employee employee);

	UserDetails getUserByUserName(String username);

	int register(Users user);

}
