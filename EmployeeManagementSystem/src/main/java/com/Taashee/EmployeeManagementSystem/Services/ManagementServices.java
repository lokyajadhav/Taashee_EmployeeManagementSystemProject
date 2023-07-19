package com.Taashee.EmployeeManagementSystem.Services;

import java.util.List;

import com.Taashee.EmployeeManagementSystem.Models.Employee;
import com.Taashee.EmployeeManagementSystem.Models.Users;

public interface ManagementServices {

	List<Employee> getEmployee();

	List<Employee> addEmployee(Employee employee);

	List<Employee> deleteEmployee(String employee_id);

	Employee getEmployee(String employee_id);

	List<Employee> updateEmployee(Employee employee);

	int register(Users user);

}
