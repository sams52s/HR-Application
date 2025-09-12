package com.leads.backend.controller;

import com.leads.backend.dto.EmployeeDto;
import com.leads.backend.model.Employee;
import com.leads.backend.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
  private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping
  public List<EmployeeDto> getAllIsDeletedFalseEmployees() {
    return employeeService.findAllIsDeletedFalse();
  }
  @GetMapping("/admin-view/all")
  public List<EmployeeDto> getAllEmployees() {
    return employeeService.findAllEmployees();
  }

  @GetMapping("/{id}")
  public EmployeeDto getEmployeeById(@PathVariable String id) {
    return employeeService.findById(id);
  }

  @PostMapping
  public EmployeeDto createEmployee(@Valid @RequestBody EmployeeDto dto) {
    return employeeService.save(dto);
  }

  @PutMapping("/{id}")
  public EmployeeDto updateEmployee(@PathVariable String id, @RequestBody EmployeeDto dto) {
    return employeeService.update(id, dto);
  }

  @DeleteMapping("/{id}")
  public void deleteEmployee(@PathVariable String id) {
    employeeService.delete(id);
  }
}
