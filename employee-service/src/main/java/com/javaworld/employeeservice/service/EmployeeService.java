package com.javaworld.employeeservice.service;

import com.javaworld.employeeservice.dto.APIResponseDTO;
import com.javaworld.employeeservice.dto.EmployeeDTO;

public interface EmployeeService {
    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
    APIResponseDTO getEmployeeById(Long employeeId);
}
