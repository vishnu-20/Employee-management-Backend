  package com.infy.irs;


import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.boot.test.context.SpringBootTest;

import com.vorg.irs.dto.LoginDTO;
import com.vorg.irs.entity.Employee;
import com.vorg.irs.exception.EmployeeException;
import com.vorg.irs.repository.EmployeeRepository;
import com.vorg.irs.service.EmployeeServiceImpl;


@SpringBootTest
class EmployeeDetailsApplicationTests {
	

	@Mock
	EmployeeRepository employeeRepo;
	
	
	@InjectMocks
	EmployeeServiceImpl employeeServiceImpl;
	

	@Test
	void getSpecificEmployeeTest() throws EmployeeException   {
		
		Integer id=45;
		Employee employeeDTO= new Employee();
	
		employeeDTO.setEmployeeId(id);
		employeeDTO.setFirstName("Vishnu");
		employeeDTO.setLastName("lal");
		employeeDTO.setDateOfJoining(LocalDate.of(2000, 04, 02));
		employeeDTO.setSalary(20000l);
		employeeDTO.setAge(22);
		employeeDTO.setDesignation("manager");
		employeeDTO.setPassword("Vishnu@123");
		employeeDTO.setPhone("9207450908");
		Optional<Employee> optional=Optional.of(employeeDTO);
		
	Mockito.when(employeeRepo.findById(id)).thenReturn(optional);
	
	Assertions.assertNotNull(employeeServiceImpl.getEmployee(id));
	  
	}

	@Test
	public void logginTest() throws EmployeeException {
		
		LoginDTO dto=new LoginDTO();
		dto.setPhone("9207450908");
		dto.setPassword("Vishnu@123");
		Employee employeeDTO= new Employee();
		
		employeeDTO.setEmployeeId(45);
		employeeDTO.setFirstName("Vishnu");
		employeeDTO.setLastName("lal");
		employeeDTO.setDateOfJoining(LocalDate.of(2000, 04, 02));
		employeeDTO.setSalary(20000l);
		employeeDTO.setAge(22);
		employeeDTO.setDesignation("manager");
		employeeDTO.setPassword("Vishnu@123");
		employeeDTO.setPhone("9207450908");
		
		Mockito.when(employeeRepo.findByPhone(dto.getPhone())).thenReturn(employeeDTO);
         String a=employeeServiceImpl.login(dto);
		Assertions.assertEquals("SUCCESS", a);
	}
	
	@Test
	public void logginIvalidTest() throws EmployeeException {
		Integer id=45;
		String phone="9207450908";
		LoginDTO dto=new LoginDTO();
		dto.setPhone("9207450908");
		dto.setPassword("Vishnu@12");
		
		Employee employeeDTO= new Employee();
		employeeDTO.setEmployeeId(id);
		employeeDTO.setFirstName("Vishnu");
		employeeDTO.setLastName("lal");
		employeeDTO.setDateOfJoining(LocalDate.of(2000, 04, 02));
		employeeDTO.setSalary(20000l);
		employeeDTO.setAge(22);
		employeeDTO.setDesignation("manager");
		employeeDTO.setPassword("Vishnu@123");
		employeeDTO.setPhone(phone);
		
		Mockito.when(employeeRepo.findByPhone(phone)).thenReturn(employeeDTO);
      
		EmployeeException exception=Assertions.assertThrows(EmployeeException.class, 
				()->employeeServiceImpl.login(dto));
		
		
		
		Assertions.assertEquals("failed", exception.getMessage());
	}
	
}
