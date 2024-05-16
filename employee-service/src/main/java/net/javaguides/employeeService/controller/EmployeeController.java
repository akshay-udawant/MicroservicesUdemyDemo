package net.javaguides.employeeService.controller;

import lombok.AllArgsConstructor;
import net.javaguides.employeeService.dto.APIResponseDto;
import net.javaguides.employeeService.dto.EmployeeDto;
import net.javaguides.employeeService.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpClient;

@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeeController {

    EmployeeService employeeService;
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto employeeDto1 = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(employeeDto1, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable ("id") Long employeeId){
        APIResponseDto apiResponseDto =  employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(apiResponseDto,HttpStatus.OK);
    }
}
