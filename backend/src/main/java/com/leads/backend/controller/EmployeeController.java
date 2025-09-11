package com.leads.backend.controller;

import com.leads.backend.model.Employee;
import com.leads.backend.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
  private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping
  public List<Employee> getEmployees() {
    return employeeService.findAllByIsDeletedFalse();
  }

  @GetMapping("/with-deleted")
  public List<Employee> getAllEmployees() {
    return employeeService.findAll();
  }

  @GetMapping("/{id}")
  public Employee getEmployee(@PathVariable String id) {
    return employeeService.findById(id);
  }

  @PostMapping
  public Employee createEmployee(@RequestBody Employee employee) {
    return employeeService.save(employee);
  }

  @PostMapping("/{id}")
  public Employee updateEmployee(@PathVariable String id, @RequestBody Employee employee) {
    employee.setEmployeeId(id);
    return employeeService.save(employee);
  }

  @DeleteMapping("/{id}")
  public void deleteEmployee(@PathVariable String id) {
    employeeService.delete(id);
  }
}
