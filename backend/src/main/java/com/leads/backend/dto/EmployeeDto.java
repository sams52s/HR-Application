package com.leads.backend.dto;

public record EmployeeDto(
  String employeeId,
  String firstName,
  String lastName,
  String division,
  String building,
  String title,
  String room
) {
}
