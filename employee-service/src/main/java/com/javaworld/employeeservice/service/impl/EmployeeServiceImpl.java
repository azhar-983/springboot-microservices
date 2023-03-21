package com.javaworld.employeeservice.service.impl;

import com.javaworld.employeeservice.EmployeeServiceApplication;
import com.javaworld.employeeservice.dto.APIResponseDTO;
import com.javaworld.employeeservice.dto.DepartmentDTO;
import com.javaworld.employeeservice.dto.EmployeeDTO;
import com.javaworld.employeeservice.entity.Employee;
import com.javaworld.employeeservice.repository.EmployeeRepository;
import com.javaworld.employeeservice.service.APIClient;
import com.javaworld.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    //private RestTemplate restTemplate;
    private WebClient webClient;
    private APIClient apiClient;
    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO,Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDTO savedEmployeeDTO = modelMapper.map(savedEmployee,EmployeeDTO.class);
        return savedEmployeeDTO;
    }

    //@CircuitBreaker(name="${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Retry(name="${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDTO getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();

        LOGGER.info("inside getEmployeeById method");
        //using RestTemplate option for communication between REST APIs
        /*ResponseEntity<DepartmentDTO> responseEntity = restTemplate.getForEntity(
                "http://localhost:8080/api/departments/"+employee.getDeptCode(),
                DepartmentDTO.class);
        DepartmentDTO departmentDTO = responseEntity.getBody();*/

        //using webclient option for communication between REST APIs
        DepartmentDTO departmentDTO = webClient.get()
                 .uri("http://localhost:8080/api/departments/"+employee.getDeptCode())
                 .retrieve()
                 .bodyToMono(DepartmentDTO.class)
                 .block();

        //Using OpenFeign option to invoke REST API
        //DepartmentDTO departmentDTO = apiClient.getDepartment(employee.getDeptCode());

        EmployeeDTO employeeDTO = modelMapper.map(employee,EmployeeDTO.class);
        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        apiResponseDTO.setEmployeeDTO(employeeDTO);
        apiResponseDTO.setDepartmentDTO(departmentDTO);

        return apiResponseDTO;
    }

    public APIResponseDTO getDefaultDepartment(Long employeeId, Exception exception) {

        LOGGER.info("inside getDefaultDepartment method");
        Employee employee = employeeRepository.findById(employeeId).get();

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDeptCode("RD001");
        departmentDTO.setDeptName("Research & Development");
        departmentDTO.setDeptDesc("Research and Development department");
        EmployeeDTO employeeDTO = modelMapper.map(employee,EmployeeDTO.class);
        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        apiResponseDTO.setEmployeeDTO(employeeDTO);
        apiResponseDTO.setDepartmentDTO(departmentDTO);

        return apiResponseDTO;

    }
}
