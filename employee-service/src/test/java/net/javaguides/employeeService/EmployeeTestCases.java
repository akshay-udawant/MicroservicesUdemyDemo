package net.javaguides.employeeService;


import lombok.AllArgsConstructor;
import net.javaguides.employeeService.dto.APIResponseDto;
import net.javaguides.employeeService.dto.EmployeeDto;
import net.javaguides.employeeService.entity.Employee;
import net.javaguides.employeeService.repository.EmployeeRepository;
import net.javaguides.employeeService.service.EmployeeService;
import net.javaguides.employeeService.service.impl.EmployeeServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeTestCases {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testCreateEmployee(){
        EmployeeDto employee = new EmployeeDto();
        employee.setFirstName("Ram");
        employee.setLastName("kumar");
        employee.setEmail("ram1234@gmail.com");
        employee.setDepartmentCode("HR001");
        EmployeeDto savedUser =  employeeService.saveEmployee(employee);
        Assert.assertNotNull(savedUser.getId());
        Assert.assertEquals("ram1234@gmail.com", savedUser.getEmail());
    }

    @Test
    public void testGetEmployeeById(){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("akshay");
        employeeDto.setEmail("akshay1234@gmail.com");
        EmployeeDto emplosaveEmployeeyeeDto1 = employeeService.saveEmployee(employeeDto);
        Long empID = emplosaveEmployeeyeeDto1.getId();
        APIResponseDto retrivedEmployee = employeeService.getEmployeeById(empID);
        assertNotNull(retrivedEmployee);
        assertEquals(empID, retrivedEmployee.getEmployee().getId());

    }



}
