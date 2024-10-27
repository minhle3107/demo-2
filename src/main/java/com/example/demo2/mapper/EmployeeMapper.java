package com.example.demo2.mapper;

import com.example.demo2.dto.EmployeeDTO;
import com.example.demo2.entity.Employee;
import com.example.demo2.model.EmployeeModel;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public EmployeeDTO toDto(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .first_name(employee.getFirst_name())
                .last_name(employee.getLast_name())
                .avatar(employee.getAvatar())
                .created_at(employee.getCreated_at())
                .updated_at(employee.getUpdated_at())
                .deleted_at(employee.getDeleted_at())
                .build();
    }

    public Employee toEntity(EmployeeModel employeeModel) {
        return Employee.builder()
                .first_name(employeeModel.getFirst_name())
                .last_name(employeeModel.getLast_name())
                .avatar(employeeModel.getAvatar())
                .build();
    }
}
