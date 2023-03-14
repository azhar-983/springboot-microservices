package com.javaworld.employeeservice.service;

import com.javaworld.employeeservice.dto.APIResponseDTO;
import com.javaworld.employeeservice.dto.DepartmentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(url="http://localhost:8080",value="DEPARTMENT-SERVICE")
@FeignClient(name = "DEPARTMENT-SERVICE") // load balanced using eureka server app name
public interface APIClient {
    @GetMapping("api/departments/{code}")
    DepartmentDTO getDepartment(@PathVariable("code") String deptCode);
}
