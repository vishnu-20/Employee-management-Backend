package com.vorg.irs.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vorg.irs.dto.EmployeeDTO;
import com.vorg.irs.dto.LoginDTO;
import com.vorg.irs.exception.EmployeeException;
import com.vorg.irs.service.EmployeeService;
import com.vorg.irs.service.EmployeeServiceImpl;

@RestController
@CrossOrigin
public class EmployeeDetailsController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private Environment environment;

	@GetMapping(value = "employee/{employeeId}")
	public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Integer employeeId) throws EmployeeException {

		EmployeeDTO dto = employeeService.getEmployee(employeeId);
		return new ResponseEntity<>(dto, HttpStatus.OK);

	}

	@GetMapping("/employee/all")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployee() throws EmployeeException {

		List<EmployeeDTO> employee = employeeService.getAllEmployee();

		return new ResponseEntity<List<EmployeeDTO>>(employee, HttpStatus.OK);

	}

	@PostMapping("/employee")
	public ResponseEntity<String> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) throws EmployeeException {

		Integer employeeId = employeeService.addEmployee(employeeDTO);
		String successMessage = environment.getProperty("UserInterface.EMPLOYEE_ADDED") + employeeId;

		return new ResponseEntity<String>(successMessage, HttpStatus.CREATED);

	}

	@PutMapping("/{employeeId}/{firstName}")
	public ResponseEntity<String> updateEmployee(@PathVariable Integer employeeId, @PathVariable String firstName)
			throws EmployeeException {

		employeeService.updateEmployee(firstName, employeeId);
		String successMessage = environment.getProperty("UserInterface.EMPLOYEE_UPDATED");

		return new ResponseEntity<String>(successMessage, HttpStatus.OK);

	}

	@DeleteMapping("employee/{employeeId}")
	public ResponseEntity<String> updateEmployee(@PathVariable Integer employeeId) throws EmployeeException {

		employeeService.deleteCustomer(employeeId);
		String successMessage = environment.getProperty("UserInterface.EMPLOYEE_DELETED");

		return new ResponseEntity<String>(successMessage, HttpStatus.OK);

	}

	@PostMapping(value = "/employee/login")
	public String login(@RequestBody LoginDTO loginDTO) throws EmployeeException {
		String s = employeeService.login(loginDTO);
//		return new ResponseEntity<EmployeeDTO>(employeeDTO,HttpStatus.OK);
		return s;
	}

	@GetMapping("/employee")
	public ResponseEntity<List<EmployeeDTO>> getByDesignation(@RequestParam("designation") String designation)
			throws EmployeeException {

		List<EmployeeDTO> list = employeeService.getAllOfSpecificLocation(designation);
		return new ResponseEntity<List<EmployeeDTO>>(list, HttpStatus.OK);

	}

}

class A {
	String name;

	public A(String name) {
		super();
		this.name = name;
	}

	public A() {
		super();
	}

}
