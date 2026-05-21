package com.challenge.api.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateEmployeeResponse {

    private String message;
    private UUID uuid;
}