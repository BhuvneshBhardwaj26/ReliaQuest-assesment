package com.challenge.api.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class EmployeeResponse {

    private UUID uuid;
    private String firstName;
    private String lastName;
    private String fullName;
    private Integer salary;
    private Integer age;
    private String jobTitle;
    private String email;
    private Instant contractHireDate;
    private Instant contractTerminationDate;
}
