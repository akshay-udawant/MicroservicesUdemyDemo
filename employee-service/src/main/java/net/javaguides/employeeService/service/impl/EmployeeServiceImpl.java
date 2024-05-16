package net.javaguides.employeeService.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.employeeService.dto.APIResponseDto;
import net.javaguides.employeeService.dto.DepartmentDto;
import net.javaguides.employeeService.dto.EmployeeDto;
import net.javaguides.employeeService.entity.Employee;
import net.javaguides.employeeService.exception.EmailAlreadyExistsException;
import net.javaguides.employeeService.exception.ResourceNotFoundException;
import net.javaguides.employeeService.mapper.AutoEmployeeMapper;
import net.javaguides.employeeService.repository.EmployeeRepository;
import net.javaguides.employeeService.service.APIClient;
import net.javaguides.employeeService.service.EmployeeService;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;
//    private RestTemplate restTemplate;
//    private WebClient webClient;
    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );
        Optional<Employee> optional =  employeeRepository.findByEmail(employeeDto.getEmail());
        System.out.println("+++++++++++++++++++++"+employeeDto.getDepartmentCode());
        if(optional.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exist");
        }

//        Employee employee = AutoEmployeeMapper.MAPPER.mapToEmployee(employeeDto);
        Employee saveEmployee = employeeRepository.save(employee);
        System.out.println("------------"+saveEmployee.getDepartmentCode());

//        EmployeeDto employeeDto1 = AutoEmployeeMapper.MAPPER.mapToEmployeeDto(saveEmployee);
        EmployeeDto employeeDto1 = new EmployeeDto(
                saveEmployee.getId(),
                saveEmployee.getFirstName(),
                saveEmployee.getLastName(),
                saveEmployee.getEmail(),
                saveEmployee.getDepartmentCode()

        );
        System.out.println("------------"+employeeDto1.getDepartmentCode());
        return employeeDto1;
    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {
        employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee","id",employeeId));
        Employee employee = employeeRepository.findById(employeeId).get();

        /*Rest Template Microservices communication*/
//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/"+employee.getDepartmentCode(),
//                DepartmentDto.class);
//        DepartmentDto departmentDto =  responseEntity.getBody();


//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/api/departments/"+employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();        // use to make it synchronous call

        /*Open Feign Microservices communication*/
        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());
        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );
//        EmployeeDto employeeDto =  AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }
}
