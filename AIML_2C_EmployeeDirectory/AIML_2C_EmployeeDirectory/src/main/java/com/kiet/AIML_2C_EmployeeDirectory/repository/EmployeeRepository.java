package com.kiet.AIML_2C_EmployeeDirectory.repository;

import com.kiet.AIML_2C_EmployeeDirectory.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByDepartment(String department);
    List<Employee> findByNameContainingIgnoreCase(String name);
}