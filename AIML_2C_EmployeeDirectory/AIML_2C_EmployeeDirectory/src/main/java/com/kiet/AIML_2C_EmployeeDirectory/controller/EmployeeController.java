package com.kiet.AIML_2C_EmployeeDirectory.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kiet.AIML_2C_EmployeeDirectory.model.Employee;
import com.kiet.AIML_2C_EmployeeDirectory.repository.EmployeeRepository;

@RestController
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // GET /employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // GET /employee/{id}
    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // GET /employees/department/{dept}
    @GetMapping("/employees/department/{dept}")
    public List<Employee> getByDepartment(@PathVariable String dept) {
        return employeeRepository.findByDepartment(dept);
    }

    // POST /employee
    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // PUT /employee/{id}
    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updated) {
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            Employee existing = optional.get();
            existing.setName(updated.getName());
            existing.setEmail(updated.getEmail());
            existing.setDepartment(updated.getDepartment());
            existing.setDesignation(updated.getDesignation());
            return employeeRepository.save(existing);
        } else {
            return null;
        }
    }

    // DELETE /employee/{id}
    @DeleteMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return "Deleted successfully.";
        } else {
            return "Employee not found.";
        }
    }
}
