package com.javaworld.departmentservice.service;

import com.javaworld.departmentservice.dto.DepartmentDTO;

public interface DepartmentService {
    DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);

    DepartmentDTO getDeptByCode(String code);

}
