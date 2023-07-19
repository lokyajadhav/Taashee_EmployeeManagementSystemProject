package com.Taashee.EmployeeManagementSystem.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.Taashee.EmployeeManagementSystem.Models.Employee;
import com.Taashee.EmployeeManagementSystem.Models.Users;
@Repository
public class ManagementDaoImplementation implements ManagementDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public List<Employee> getEmployees() {
		String getQuety="SELECT * from employee";
		return jdbcTemplate.query(getQuety, new BeanPropertyRowMapper<Employee>(Employee.class));
	}
	@Override
	public List<Employee> addEmployee(Employee employee) {
		String addQuery="insert into employee(firstname,lastname,employee_id,address,age,qualification,designation) values(?,?,?,?,?,?,?)";
		
		jdbcTemplate.update(addQuery, new Object[] {employee.getFirstname(),employee.getLastname(),employee.getEmployee_id(),employee.getAddress(),employee.getAge(),employee.getQualification(),employee.getDesignation()});
		return getEmployees();
	}
	@Override
	public List<Employee> deleteEmployee(String employee_id) {
		String delQuery="DELETE from employee where employee_id=?";
		jdbcTemplate.update(delQuery,new Object[] {employee_id});
		return getEmployees();
	}
	@Override
	public Employee getEmployee(String employee_id) {
		String getQuery="Select * from employee where employee_id=?";
		return jdbcTemplate.queryForObject(getQuery, new BeanPropertyRowMapper<Employee>(Employee.class),new Object[] {employee_id});
	}
	@Override
	public List<Employee> updateEmployee(Employee employee) {
		String updateQuery="UPDATE employee set firstname=?,lastname=?,address=?, age=?, qualification=?,designation=? where employee_id=?";
		jdbcTemplate.update(updateQuery,new Object[] {employee.getFirstname(),employee.getLastname(),employee.getAddress(),employee.getAge(),employee.getQualification(),employee.getDesignation(), employee.getEmployee_id()});
		return getEmployees();
	}
	@Override
	public UserDetails getUserByUserName(String username) {
		
		String getQuery="SELECT * from users where username=?";
		return jdbcTemplate.queryForObject(getQuery, new BeanPropertyRowMapper<Users>(Users.class), new Object[] {username});
	
	}
	@Override
	public int register(Users user) {
		String addQuery="INSERT INTO users(username,password,email,  mobile, role) values(?,?,?,?,?)";
		return jdbcTemplate.update(addQuery, new Object[] {user.getUsername(), user.getPassword(), user.getEmail(),  user.getMobile(), user.getRole()});
	}

}
