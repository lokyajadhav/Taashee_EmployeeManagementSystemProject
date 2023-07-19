package com.Taashee.EmployeeManagementSystem.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Taashee.EmployeeManagementSystem.Dao.ManagementDao;
import com.Taashee.EmployeeManagementSystem.Models.Employee;
import com.Taashee.EmployeeManagementSystem.Models.Users;
@Service
public class ManagementServiceImplementation implements ManagementServices {
	@Autowired
	ManagementDao managementDao;
	@Override
	public List<Employee> getEmployee() {
		
		return managementDao.getEmployees();
	}
	@Override
	public List<Employee> addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return managementDao.addEmployee(employee);
	}
	@Override
	public List<Employee> deleteEmployee(String employee_id) {
		
		return managementDao.deleteEmployee(employee_id);
	}
	@Override
	public Employee getEmployee(String employee_id) {
		// TODO Auto-generated method stub
		return managementDao.getEmployee(employee_id);
	}
	@Override
	public List<Employee> updateEmployee(Employee employee) {
		
		return managementDao.updateEmployee(employee);
	}
	@Override
	public int register(Users user) {
		// TODO Auto-generated method stub
		return managementDao.register(user);
	}

}
