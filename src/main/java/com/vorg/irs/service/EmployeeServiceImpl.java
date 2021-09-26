package com.vorg.irs.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vorg.irs.dto.EmployeeCoversionClass;
import com.vorg.irs.dto.EmployeeDTO;
import com.vorg.irs.dto.LoginDTO;
import com.vorg.irs.entity.Employee;
import com.vorg.irs.exception.EmployeeException;
import com.vorg.irs.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	private static Logger logger=LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	private EmployeeCoversionClass employeeCovert=new EmployeeCoversionClass();

	@Override
	public EmployeeDTO getEmployee(Integer employeeId) throws EmployeeException {
		
		Optional<Employee> optional=employeeRepo.findById(employeeId);
		    
		
		Employee employee=optional.orElseThrow(()-> new EmployeeException("Service.INVALID_EMPLOYEEID"));
		
		EmployeeDTO employeeDTO=employeeCovert.prepareDto(employee);
		
		return employeeDTO;
	}

	@Override
	public List<EmployeeDTO> getAllEmployee() throws EmployeeException {
		
	   Iterable<Employee> employees=employeeRepo.findAll();
	   
	   List<EmployeeDTO> employeeDTOs=new ArrayList<>();
	   
	   employees.forEach(customer ->{
		   EmployeeDTO employeeDTO=employeeCovert.prepareDto(customer);
		   employeeDTOs.add(employeeDTO);
	   });
	   if(employeeDTOs.isEmpty()) {
		   throw new EmployeeException("Service.NO_EMPLOYEE_FOUND");
	   }
	   
		
		
		return employeeDTOs;
	}

	@Override
	public void updateEmployee(String firstName,Integer employeeId) throws EmployeeException {
      
		Optional<Employee> optional=employeeRepo.findById(employeeId);
		Employee employee=optional.orElseThrow(()-> new EmployeeException("Service.INVALID_EMPLOYEEID"));
		employee.setFirstName(firstName);
		employeeRepo.save(employee);
	}

	@Override
	public void deleteCustomer(Integer employeeId) throws EmployeeException {
		Optional<Employee> optional=employeeRepo.findById(employeeId);
		Employee employee=optional.orElseThrow(()-> new EmployeeException("Service.INVALID_EMPLOYEEID"));
		employeeRepo.delete(employee);
	}

	@Override
	public Integer addEmployee(EmployeeDTO employeeDTO) throws EmployeeException {
		
		/*
		 * Optional<Employee> optional=
		 * employeeRepo.findById(employeeDTO.getEmployeeId()); if(optional.isPresent())
		 * {
		 * 
		 * throw new EmployeeException("Service.INVALID_EMPLOYEEID"); }
		 */
		
		Employee employee=employeeCovert.prepareEntity(employeeDTO);
		employeeRepo.save(employee);
		return employee.getEmployeeId();
	}

	@Override
	public String login(LoginDTO loginDTO)throws EmployeeException  {
		
		Employee employee=employeeRepo.findByPhone(loginDTO.getPhone());
		EmployeeDTO dto=new EmployeeDTO();
		if(!(loginDTO==null) && employee.getPassword().equals(loginDTO.getPassword())) {
//		dto=employeeCovert.prepareDto(employee);	
			return "SUCCESS";
		}else {
			throw new EmployeeException("failed");
		}
		
	}

	@Override
	public List<EmployeeDTO> getAllOfParticularDate(LocalDate dateOfJoining) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeDTO> getAllOfSpecificLocation(String designation) throws EmployeeException {
		// TODO Auto-generated method stub
		List<Employee> employees=  employeeRepo.findByDesignation(designation);
		List<EmployeeDTO> employeeDTOs=new ArrayList<>();
		if(employees.isEmpty()) {
			logger.error("error");
			throw new EmployeeException("Service.INVALID_DESIGNATION");
		}
		for (Employee employee : employees) {
			EmployeeDTO employeeToBeListed= employeeCovert.prepareDto(employee);
			
			employeeDTOs.add(employeeToBeListed); 
		}
		return employeeDTOs;
	}

}
