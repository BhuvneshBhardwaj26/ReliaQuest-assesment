    package com.challenge.api.service.impl;

    import com.challenge.api.dto.CreateEmployeeRequest;
    import com.challenge.api.model.Employee;    
    import com.challenge.api.service.EmployeeService;
    import com.challenge.api.dto.CreateEmployeeResponse;
    import java.util.List;
    import java.util.UUID;
    import org.springframework.stereotype.Service;
    import java.util.Map;
    import java.util.HashMap;
    import java.util.ArrayList;
    import org.springframework.http.HttpStatus;
    import org.springframework.web.server.ResponseStatusException;

    @Service
    public class EmployeeServiceImpl implements EmployeeService {

        private final Map<UUID, Employee> employeeStore = new HashMap<>();

        public EmployeeServiceImpl() {
        
        Employee employeeOne = new EmployeeImpl();

        UUID employeeOneUuid = UUID.randomUUID();

        employeeOne.setUuid(employeeOneUuid);
        employeeOne.setFirstName("John");
        employeeOne.setLastName("Doe");
        employeeOne.setFullName("John Doe");
        employeeOne.setSalary(85000);
        employeeOne.setAge(30);
        employeeOne.setJobTitle("Software Engineer");
        employeeOne.setEmail("john.doe@reliaquest.com");
        employeeOne.setContractHireDate(Instant.now());

        employeeStore.put(employeeOneUuid, employeeOne);

        Employee employeeTwo = new EmployeeImpl();

        UUID employeeTwoUuid = UUID.randomUUID();

        employeeTwo.setUuid(employeeTwoUuid);
        employeeTwo.setFirstName("Sarah");
        employeeTwo.setLastName("Smith");
        employeeTwo.setFullName("Sarah Smith");
        employeeTwo.setSalary(95000);
        employeeTwo.setAge(28);
        employeeTwo.setJobTitle("Backend Developer");
        employeeTwo.setEmail("sarah.smith@reliaquest.com");
        employeeTwo.setContractHireDate(Instant.now());

        employeeStore.put(employeeTwoUuid, employeeTwo);
    }

        @Override
        public List<EmplooyeeResponse> getAllEmployees() {
        List<EmployeeResponse> responseList = new ArrayList<>();

        for(Employee employee : employeeStore.values()){
            responseList.add(mapToEmployeeResponse(employee));
        }
        return responseList;
        }

        @Override
        public EmployeeResponse getEmployeeByUuid(UUID uuid) {
            
            Employee employee = employeeStore.get(uuid);
            if(employee == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with the provided UUID:" + uuid);
            };
            return mapToEmployeeResponse(employee);
        }

        @Override
        public Employee createEmployee(CreateEmployeeRequest request) {
            Employee employee = new EmployeeImpl();
            UUID uuid = UUID.randomUUID();
            employee.setUuid(uuid); 
            employee.setFirstName(request.getFirstName());
            employee.setLastName(request.getLastName());
            employee.setFullName(request.getFirstName() + " " + request.getLastName());
            employee.setSalary(request.getSalary());
            employee.setAge(request.getAge());
            employee.setJobTitle(request.getJobTitle());
            employee.setEmail(request.getEmail());
            employee.setDateOfJoining(request.getDateOfJoining());
            employeeStore.put(uuid, employee);
            return new CreateEmployeeResponse("Employee Created succesfully:",uuid
            );}
        private EmployeeResponse mapToEmployeeResponse(Employee employee){
            EmployeeResponse response = new EmployeeResponse();
            return new EmployeeResponse(
            response.setUuid(employee.getUuid());
            response.setFirstName(employee.getFirstName());
            response.setLastName(employee.getLastName());
            response.setFullName(employee.getFullName());
            response.setSalary(employee.getSalary());
            response.setAge(employee.getAge());
            response.setJobTitle(employee.getJobTitle());
            response.setEmail(employee.getEmail());
                response.setDateOfJoining(employee.getDateOfJoining()
            );

            return response;
        }

    }
