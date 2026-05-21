package com.challenge.api.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;


@Data
public class CreateEmployeeRequest {
    @NotBlank
    private String firstName;
    private String lastName;
    private Integer salary;
    private Integer age;
    private String jobTitle;
    private String email;  
}
