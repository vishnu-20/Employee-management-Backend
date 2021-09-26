package com.vorg.irs.dto;

import java.time.LocalDate;


import javax.validation.constraints.Pattern;

public class EmployeeDTO {
	

	private Integer employeeId;
	
	@Pattern(regexp = "[A-Za-z]+",message = "{employee.name.invalid}")	
	private String firstName;
	@Pattern(regexp = "[A-Za-z]+",message = "{employee.name.invalid}")
	private String lastName;
	
	private LocalDate dateOfJoining;
	private Long salary;
	private int age;
	private String designation;
	private String password;	
	@Pattern(regexp = "[7-9]{1}[0-9]{9}",message = "{phone.INVALID}")
	private String phone;
	public Integer getEmployeeId() {
		return employeeId;
	}
	
	
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public Long getSalary() {
		return salary;
	}
	public void setSalary(Long salary) {
		this.salary = salary;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	

}
