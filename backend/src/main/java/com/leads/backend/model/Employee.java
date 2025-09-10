package com.leads.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee {
  @Id
  private String employeeId;

  private String firstName;

  private String lastName;

  private String division;

  private String building;

  private String title;

  private String room;

  @Column(name = "IS_DELETED", nullable = false)
  private boolean isDeleted = false;

}

