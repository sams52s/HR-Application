package com.leads.backend.dto;

import jakarta.validation.constraints.NotBlank;

public record EmployeeDto(
  @NotBlank String employeeId,
  @NotBlank String firstName,
  @NotBlank String lastName,
  String division,
  String building,
  String title,
  String room
) {}
