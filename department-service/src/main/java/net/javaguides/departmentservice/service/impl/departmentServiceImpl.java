package net.javaguides.departmentservice.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.departmentservice.dto.DepartmentDto;
import net.javaguides.departmentservice.entity.Department;
import net.javaguides.departmentservice.exception.DepartmentAlreadyExistsException;
import net.javaguides.departmentservice.mapper.AutoDepartmentMapper;
import net.javaguides.departmentservice.repository.DepartmentRepository;
import net.javaguides.departmentservice.service.DepartmentService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class departmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveUser(DepartmentDto departmentDto) {
        //convert departmentdto into department jpa entity
//        Department department = new Department(
//                departmentDto.getId(),
//                departmentDto.getDepartmentName(),
//                departmentDto.getDepartmentDescription(),
//                departmentDto.getDepartmentCode()
//        );
          Department department1 =  departmentRepository.findByDepartmentCode(departmentDto.getDepartmentCode());
          if(department1!=null){
              throw new DepartmentAlreadyExistsException("Department already exists");
          }
          Department department =  AutoDepartmentMapper.MAPPER.mapToDepartment(departmentDto);
          Department saveDepartment = departmentRepository.save(department);


        //convert department jpa entity into departmentdto

//        DepartmentDto departmentDto1 = new DepartmentDto(
//                saveDepartment.getId(),
//                saveDepartment.getDepartmentName(),
//                saveDepartment.getDepartmentDescription(),
//                saveDepartment.getDepartmentCode()
//        );
        DepartmentDto departmentDto1 = AutoDepartmentMapper.MAPPER.mapToDepartmentDto(saveDepartment);
        return departmentDto1;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department =  departmentRepository.findByDepartmentCode(departmentCode);

        DepartmentDto departmentDto = new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
        return departmentDto;
    }
}
