package com.example.demo2.service.impl;

import com.example.demo2.dto.EmployeeDTO;
import com.example.demo2.entity.Employee;
import com.example.demo2.mapper.EmployeeMapper;
import com.example.demo2.model.EmployeeModel;
import com.example.demo2.repository.IEmployeeRepository;
import com.example.demo2.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private IEmployeeRepository iEmployeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    private List<EmployeeDTO> mappingToDto(List<Employee> employees) {

        List<EmployeeDTO> employeeDTOList = new ArrayList<>();

        for (Employee employee : employees) {
            employeeDTOList.add(employeeMapper.toDto(employee));
        }

        return employeeDTOList;
    }

    @Override
    public List<EmployeeDTO> getAll() {

        return mappingToDto(iEmployeeRepository.getAll());
    }

    @Override
    public long create(EmployeeModel employeeModel) {

        Employee employeeCreate = employeeMapper.toEntity(employeeModel);
        return iEmployeeRepository.create(employeeCreate);
    }

    @Override
    public EmployeeDTO update(long id, EmployeeModel employeeModel) {

        Employee employeeExisted = iEmployeeRepository.getById(id);

        if (employeeExisted == null) {
            throw new RuntimeException("Employee does not exist");
        }

        Employee employeeUpdate = employeeMapper.toEntity(employeeModel);

        return employeeMapper.toDto(iEmployeeRepository.update(id, employeeUpdate));
    }

    @Override
    public long delete(long id) {
        return iEmployeeRepository.delete(id);
    }

    @Override
    public EmployeeDTO getById(long id) {
        return employeeMapper.toDto(iEmployeeRepository.getById(id));
    }
}
