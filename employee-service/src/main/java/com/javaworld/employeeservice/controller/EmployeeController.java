package com.javaworld.employeeservice.controller;

import com.javaworld.employeeservice.dto.APIResponseDTO;
import com.javaworld.employeeservice.dto.EmployeeDTO;
import com.javaworld.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employee){
        EmployeeDTO employeeDTO = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<APIResponseDTO> getEmployee(@PathVariable("id") Long employeeId){
        APIResponseDTO apiResponseDTO = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(apiResponseDTO,HttpStatus.OK);
    }
}
