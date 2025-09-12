package com.leads.backend.service;

import com.leads.backend.dto.EmployeeDto;
import com.leads.backend.mapper.EmployeeMapper;
import com.leads.backend.model.Employee;
import com.leads.backend.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EmployeeService {
  private final EmployeeRepository employeeRepository;
  private final EmployeeMapper employeeMapper;

  public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
    this.employeeRepository = employeeRepository;
    this.employeeMapper = employeeMapper;
  }

  public List<EmployeeDto> findAllIsDeletedFalse() {
    return employeeRepository.findAllByIsDeletedFalse().stream()
      .map(employeeMapper::toDto)
      .toList();
  }

  public List<EmployeeDto> findAllEmployees() {
    return  employeeRepository.findAll().stream()
      .map(employeeMapper::toDto)
      .toList();
  }

  public EmployeeDto findById(String id) {
    Employee employee = employeeRepository.findById(id)
      .filter(emp -> !emp.isDeleted())
      .orElseThrow(() -> new RuntimeException("Employee with id " + id + " not found"));
    return employeeMapper.toDto(employee);
  }

  public EmployeeDto save(EmployeeDto dto) {
    Employee employee = employeeMapper.toEntity(dto);
    employee.setDeleted(false);
    Employee savedEmployee = employeeRepository.save(employee);
    return employeeMapper.toDto(savedEmployee);
  }

  public EmployeeDto update(String id, EmployeeDto dto) {
    Employee existingEmployee = employeeRepository.findById(id)
      .filter(emp -> !emp.isDeleted())
      .orElseThrow(() -> new RuntimeException("Employee with id " + id + " not found"));
    employeeMapper.updateEntityFromDto(dto, existingEmployee);
    Employee updatedEmployee = employeeRepository.save(existingEmployee);
    return employeeMapper.toDto(updatedEmployee);
  }

  public void delete(String id) {
    Employee existingEmployee = employeeRepository.findById(id)
      .filter(emp -> !emp.isDeleted())
      .orElseThrow(() -> new RuntimeException("Employee with id " + id + " not found"));
    existingEmployee.setDeleted(true);
    employeeRepository.save(existingEmployee);
  }
}
