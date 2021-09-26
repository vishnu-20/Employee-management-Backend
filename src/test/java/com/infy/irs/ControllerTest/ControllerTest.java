package com.infy.irs.ControllerTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.vorg.irs.controller.EmployeeDetailsController;
import com.vorg.irs.dto.EmployeeDTO;
import com.vorg.irs.exception.EmployeeException;
import com.vorg.irs.service.EmployeeService;

@SpringBootTest
public class ControllerTest {
	
	@Mock
	EmployeeService employeeService;
	
	@InjectMocks
	EmployeeDetailsController detailsController;
	
	@Test
	public  void getEmployeeControllerTest() throws EmployeeException{
		
		Integer id=45;
		
		EmployeeDTO dto=new EmployeeDTO();
		dto.setEmployeeId(id);
		
		Mockito.when(employeeService.getEmployee(id)).thenReturn(dto);
		
		assertNotNull(detailsController.getEmployee(id));
		
		
		
	}

}
