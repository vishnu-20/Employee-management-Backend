package com.vorg.irs.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vorg.irs.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	public Employee findByPhone(String phone);
	public List<Employee> findByDateOfJoining(LocalDate dateOfJoining);
	public List<Employee> findByDesignation(String designation);
}
