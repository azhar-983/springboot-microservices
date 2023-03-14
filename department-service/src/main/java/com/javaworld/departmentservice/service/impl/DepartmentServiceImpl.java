package com.javaworld.departmentservice.service.impl;

import com.javaworld.departmentservice.dto.DepartmentDTO;
import com.javaworld.departmentservice.entity.Department;
import com.javaworld.departmentservice.repository.DepartmentRepository;
import com.javaworld.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepository departmentRepository;

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        //convert departmentDTO to Department JPA entity
        Department department = new Department(departmentDTO.getId(),
                departmentDTO.getDeptName(),
                departmentDTO.getDeptDesc(),
                departmentDTO.getDeptCode());
        Department savedDepartment = departmentRepository.save(department);

        //convert department entity to departmentDTO
        DepartmentDTO savedDepartmentDTO = new DepartmentDTO(savedDepartment.getId(),
                savedDepartment.getDeptName(),
                savedDepartment.getDeptDesc(),
                savedDepartment.getDeptCode());
        return savedDepartmentDTO;
    }

    @Override
    public DepartmentDTO getDeptByCode(String code) {
        Department department = departmentRepository.findByDeptCode(code);

        return new DepartmentDTO(department.getId(),
                department.getDeptName(),
                department.getDeptDesc(),
                department.getDeptCode());
    }
}
