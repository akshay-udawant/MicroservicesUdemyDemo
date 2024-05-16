package net.javaguides.employeeService.mapper;

import net.javaguides.employeeService.dto.EmployeeDto;
import net.javaguides.employeeService.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoEmployeeMapper {
    AutoEmployeeMapper MAPPER = Mappers.getMapper(AutoEmployeeMapper.class);
    public EmployeeDto mapToEmployeeDto(Employee employee);
    public Employee mapToEmployee(EmployeeDto employeeDto);
}
