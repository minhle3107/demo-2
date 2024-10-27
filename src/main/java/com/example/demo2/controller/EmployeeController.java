package com.example.demo2.controller;

import com.example.demo2.dto.EmployeeDTO;
import com.example.demo2.model.EmployeeModel;
import com.example.demo2.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    @Autowired
    private IEmployeeService iEmployeeService;

    @PostMapping()
    public long create(@RequestBody EmployeeModel employeeModel) {
        return iEmployeeService.create(employeeModel);
    }

    @PutMapping("/{id}")
    public EmployeeDTO update(@PathVariable long id, @RequestBody EmployeeModel employeeModel) {
        return iEmployeeService.update(id, employeeModel);
    }

    @DeleteMapping("/{id}")
    public long delete(@PathVariable long id) {
        return iEmployeeService.delete(id);
    }

    @GetMapping
    public List<EmployeeDTO> getAll() {
        return iEmployeeService.getAll();
    }

    @GetMapping("/{id}")
    public EmployeeDTO getById(@PathVariable long id) {
        return iEmployeeService.getById(id);
    }
}