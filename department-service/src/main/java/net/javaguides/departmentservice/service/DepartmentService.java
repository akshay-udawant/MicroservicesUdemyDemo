package net.javaguides.departmentservice.service;

import net.javaguides.departmentservice.dto.DepartmentDto;

public interface DepartmentService {

    DepartmentDto saveUser(DepartmentDto departmentDto);

    DepartmentDto getDepartmentByCode(String departmentCode);
}
