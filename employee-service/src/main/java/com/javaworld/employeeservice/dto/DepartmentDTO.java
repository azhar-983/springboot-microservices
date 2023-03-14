package com.javaworld.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    private Long id;
    private String deptName;
    private String deptDesc;
    private String deptCode;
}
