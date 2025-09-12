package com.leads.backend.controller;


import com.leads.backend.dto.EmployeeDto;
import com.leads.backend.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {


  private final EmployeeRepository repo;

  public AuthController(EmployeeRepository repo) {
    this.repo = repo;
  }

  @PostMapping("/login")
  public EmployeeDto login(@RequestBody LoginRequest request) {
    return repo.findById(request.employeeId())
      .filter(emp -> !emp.isDeleted()) // only active
      .map(emp -> new EmployeeDto(
        emp.getEmployeeId(),
        emp.getFirstName(),
        emp.getLastName(),
        emp.getDivision(),
        emp.getBuilding(),
        emp.getTitle(),
        emp.getRoom()
      ))
      .orElseThrow(() -> new RuntimeException("Invalid credentials"));
  }

  public record LoginRequest(String employeeId, String password) {}
}
