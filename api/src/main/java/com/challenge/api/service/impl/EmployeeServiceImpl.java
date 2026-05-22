package com.challenge.api.service.impl;

import com.challenge.api.dto.CreateEmployeeRequest;
import com.challenge.api.dto.CreateEmployeeResponse;
import com.challenge.api.dto.EmployeeResponse;
import com.challenge.api.model.Employee;
import com.challenge.api.model.impl.EmployeeImpl;
import com.challenge.api.service.EmployeeService;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<UUID, Employee> employeeStore = new HashMap<>();

    public EmployeeServiceImpl() {

        Employee employeeOne = new EmployeeImpl();

        UUID employeeOneUuid = UUID.randomUUID();

        employeeOne.setUuid(employeeOneUuid);
        employeeOne.setFirstName("Bhuvnesh");
        employeeOne.setLastName("Bhardwaj");
        employeeOne.setFullName("Bhuvnesh Bhardwaj");
        employeeOne.setSalary(85000);
        employeeOne.setAge(30);
        employeeOne.setJobTitle("Software Engineer");
        employeeOne.setEmail("bhuvneshbhardwaj49@gmail.com");
        employeeOne.setContractHireDate(Instant.now());

        employeeStore.put(employeeOneUuid, employeeOne);

        Employee employeeTwo = new EmployeeImpl();

        UUID employeeTwoUuid = UUID.randomUUID();

        employeeTwo.setUuid(employeeTwoUuid);
        employeeTwo.setFirstName("Samarth Mishra");
        employeeTwo.setLastName("Samarth Mishra");
        employeeTwo.setFullName("Samarth Mishra");
        employeeTwo.setSalary(95000);
        employeeTwo.setAge(28);
        employeeTwo.setJobTitle("Backend Developer");
        employeeTwo.setEmail("samarth78@gmail.com");
        employeeTwo.setContractHireDate(Instant.now());

        employeeStore.put(employeeTwoUuid, employeeTwo);
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {

        List<EmployeeResponse> responseList = new ArrayList<>();

        for (Employee employee : employeeStore.values()) {
            responseList.add(mapToEmployeeResponse(employee));
        }

        return responseList;
    }

    @Override
    public EmployeeResponse getEmployeeByUuid(UUID uuid) {

        Employee employee = employeeStore.get(uuid);

        if (employee == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Employee not found with provided UUID: " + uuid
            );
        }

        return mapToEmployeeResponse(employee);
    }

    @Override
    public CreateEmployeeResponse createEmployee(
            CreateEmployeeRequest request
    ) {

        Employee employee = new EmployeeImpl();

        UUID uuid = UUID.randomUUID();

        employee.setUuid(uuid);
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setFullName(
                request.getFirstName() + " " + request.getLastName()
        );
        employee.setSalary(request.getSalary());
        employee.setAge(request.getAge());
        employee.setJobTitle(request.getJobTitle());
        employee.setEmail(request.getEmail());
        employee.setContractHireDate(Instant.now());

        employeeStore.put(uuid, employee);

        return new CreateEmployeeResponse(
                "Employee created successfully",
                uuid
        );
    }

    private EmployeeResponse mapToEmployeeResponse(Employee employee) {

        EmployeeResponse response = new EmployeeResponse();

        response.setUuid(employee.getUuid());
        response.setFirstName(employee.getFirstName());
        response.setLastName(employee.getLastName());
        response.setFullName(employee.getFullName());
        response.setSalary(employee.getSalary());
        response.setAge(employee.getAge());
        response.setJobTitle(employee.getJobTitle());
        response.setEmail(employee.getEmail());
        response.setContractHireDate(employee.getContractHireDate());
        response.setContractTerminationDate(employee.getContractTerminationDate());

        return response;
    }
}