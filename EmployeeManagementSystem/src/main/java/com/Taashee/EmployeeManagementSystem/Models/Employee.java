package com.Taashee.EmployeeManagementSystem.Models;

public class Employee {
		private String firstname ;
	   private String lastname ;
	    private String  employee_id  ;
	    private String address ;
	    private int age;
	    private String qualification ;
	    private String   designation ;
		public String getFirstname() {
			return firstname;
		}
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
		public String getLastname() {
			return lastname;
		}
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		public String getEmployee_id() {
			return employee_id;
		}
		public void setEmployee_id(String employee_id) {
			this.employee_id = employee_id;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getQualification() {
			return qualification;
		}
		public void setQualification(String qualification) {
			this.qualification = qualification;
		}
		public String getDesignation() {
			return designation;
		}
		public void setDesignation(String designation) {
			this.designation = designation;
		}
		public Employee() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Employee(String firstname, String lastname, String employee_id, String address, int age,
				String qualification, String designation) {
			super();
			this.firstname = firstname;
			this.lastname = lastname;
			this.employee_id = employee_id;
			this.address = address;
			this.age = age;
			this.qualification = qualification;
			this.designation = designation;
		}
		
		
	  
	    
	
	  
	  
	  
}
