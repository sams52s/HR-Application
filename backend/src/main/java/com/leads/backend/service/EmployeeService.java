package com.leads.backend.service;

import com.leads.backend.model.Employee;
import com.leads.backend.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
  private final EmployeeRepository employeeRepository;

  public EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  public List<Employee> findAllByIsDeletedFalse() {
    return employeeRepository.findAllByIsDeletedFalse();
  }

  public List<Employee> findAll() {
    return employeeRepository.findAll();
  }

  public Employee findById(String id) {
    return employeeRepository.findById(id).filter(employee -> !employee.isDeleted()).orElse(null);
  }

  public Employee save(Employee employee) {
    return employeeRepository.save(employee);
  }

  public void delete(String id) {
    employeeRepository.findById(id).ifPresent(employee -> {
      employee.setDeleted(true);
      employeeRepository.save(employee);
    });
  }
}
