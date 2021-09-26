package com.vorg.irs.dto;

import java.time.LocalDate;

import com.vorg.irs.entity.Employee;

public class EmployeeCoversionClass {

	
	
public  EmployeeDTO prepareDto(Employee employee) {
		
		EmployeeDTO employeeDTO=new EmployeeDTO();
		employeeDTO.setAge(employee.getAge());
		employeeDTO.setDateOfJoining(employee.getDateOfJoining());
		employeeDTO.setEmployeeId(employee.getEmployeeId());
		employeeDTO.setDesignation(employee.getDesignation());
		employeeDTO.setPassword(employee.getPassword());
		employeeDTO.setPhone(employee.getPhone());
		employeeDTO.setFirstName(employee.getFirstName());
		employeeDTO.setLastName(employee.getLastName());
		employeeDTO.setSalary(employee.getSalary());
		return employeeDTO;
		
	}
	
public  Employee prepareEntity(EmployeeDTO employee) {
		
		Employee employeeDTO=new Employee();
		employeeDTO.setAge(employee.getAge());
		employeeDTO.setDateOfJoining(LocalDate.now());
//		employeeDTO.setEmployeeId(employee.getEmployeeId());
		employeeDTO.setPassword(employee.getPassword());
		employeeDTO.setPhone(employee.getPhone());
		employeeDTO.setDesignation(employee.getDesignation());
		employeeDTO.setFirstName(employee.getFirstName());
		employeeDTO.setLastName(employee.getLastName());
		employeeDTO.setSalary(employee.getSalary());
		return employeeDTO;
		
	}
}
