package com.challenge.api.dto;

import lombok.Data;

@Data
public class CreateEmployeeRequest {
    private String firstName;
    private String lastName;
    private Integer salary;
    private Integer age;
    private String jobTitle;
    private String email;  
}
