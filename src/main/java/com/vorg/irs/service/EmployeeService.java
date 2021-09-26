package com.vorg.irs.service;

import java.time.LocalDate;
import java.util.List;

import com.vorg.irs.dto.EmployeeDTO;
import com.vorg.irs.dto.LoginDTO;
import com.vorg.irs.entity.Employee;
import com.vorg.irs.exception.EmployeeException;

public interface EmployeeService {
	
	public EmployeeDTO getEmployee(Integer employeeId) throws EmployeeException;
	public Integer addEmployee(EmployeeDTO employeeDTO)throws EmployeeException ;
	public List<EmployeeDTO> getAllEmployee() throws EmployeeException;
	public void updateEmployee(String firstName,Integer employeeId) throws EmployeeException;
	public void deleteCustomer(Integer employeeId) throws EmployeeException;
	public String login(LoginDTO loginDTO) throws EmployeeException ;
	
	public List<EmployeeDTO> getAllOfParticularDate(LocalDate dateOfJoining) throws EmployeeException;
	public List<EmployeeDTO> getAllOfSpecificLocation(String designation) throws EmployeeException;

}
