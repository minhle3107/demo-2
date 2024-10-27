package com.example.demo2.repository.impl;


import com.example.demo2.dto.EmployeeDTO;
import com.example.demo2.entity.Employee;
import com.example.demo2.mapper.EmployeeMapper;
import com.example.demo2.model.EmployeeModel;
import com.example.demo2.repository.IEmployeeRepository;
import com.example.demo2.rowmapper.EmployeeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class EmployeeRepository implements IEmployeeRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EmployeeMapper employeeMapper;

//    public EmployeeRepository(JdbcTemplate jdbcTemplate, EmployeeMapper employeeMapper) {
//        this.jdbcTemplate = jdbcTemplate;
//        this.employeeMapper = employeeMapper;
//    }


    @Override
    public List<Employee> getAll() {
        String query = "SELECT * FROM employees WHERE deleted_at IS NULL";
        return jdbcTemplate.query(query, new EmployeeRowMapper());
    }

    @Override
    public long create(Employee employee) {


        String query = "INSERT INTO employees (first_name, last_name) VALUES (?, ?)";
        return jdbcTemplate.update(query, employee.getFirst_name(), employee.getLast_name());
    }

    @Override
    public Employee update(long id, Employee employee) {
        String query = "UPDATE employees SET first_name = ?, last_name = ? WHERE id = ?";
        jdbcTemplate.update(query, employee.getFirst_name(), employee.getLast_name(), id);

        return getById(id);
    }

    @Override
    public long delete(long id) {
        Date currenDate = new Date();
        String query = "UPDATE employees SET deleted_at = ? WHERE id = ?";
        jdbcTemplate.update(query, currenDate, id);
        return 1;
    }

    @Override
    public Employee getById(long id) {

        String query = "SELECT * FROM employees where id = ? AND deleted_at IS NOT NULL";

        return jdbcTemplate.queryForObject(query, new EmployeeRowMapper(), id);
    }
}
