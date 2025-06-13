package com.kiet.AIML_2C_EmployeeDirectory.controller;

import com.kiet.AIML_2C_EmployeeDirectory.model.Employee;
import com.kiet.AIML_2C_EmployeeDirectory.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeControllerMVC {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "index";
    }

    @GetMapping("/search")
    @ResponseBody
    public List<Employee> search(@RequestParam("q") String query) {
        return employeeRepository.findByNameContainingIgnoreCase(query);
    }
    
    @GetMapping("/employee/new")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "add-employee";
    }

    @PostMapping("/employee/save")
    public String saveEmployee(@ModelAttribute("employee") Employee emp) {
        employeeRepository.save(emp);
        return "redirect:/";
    }

    @GetMapping("/employee/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Employee emp = employeeRepository.findById(id).orElse(null);
        model.addAttribute("employee", emp);
        return "edit-employee";
    }

    @PostMapping("/employee/update/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute Employee emp) {
        emp.setId(id);
        employeeRepository.save(emp);
        return "redirect:/";
    }

    @GetMapping("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
        return "redirect:/";
    }
}
