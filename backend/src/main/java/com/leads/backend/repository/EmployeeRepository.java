package com.leads.backend.repository;

import com.leads.backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
  List<Employee> findAllByIsDeletedFalse();
}
