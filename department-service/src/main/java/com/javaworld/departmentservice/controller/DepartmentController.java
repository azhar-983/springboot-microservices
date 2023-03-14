package com.javaworld.departmentservice.controller;

import com.javaworld.departmentservice.dto.DepartmentDTO;
import com.javaworld.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO){
        System.out.println("entered block");
        DepartmentDTO savedDepartment = departmentService.saveDepartment(departmentDTO);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping("{code}")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable("code") String deptCode){
        DepartmentDTO departmentDTO = departmentService.getDeptByCode(deptCode);
        return new ResponseEntity<>(departmentDTO,HttpStatus.OK);
    }
}
