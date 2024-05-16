package net.javaguides.employeeService.service;

import net.javaguides.employeeService.dto.APIResponseDto;
import net.javaguides.employeeService.dto.EmployeeDto;
import net.javaguides.employeeService.entity.Employee;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    APIResponseDto getEmployeeById(Long employeeId);

}
