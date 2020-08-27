package com.ajd.tryout.graphql.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajd.tryout.graphql.entity.Employee;
import com.ajd.tryout.graphql.repo.EmployeeRepo;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepo.findById(id).orElse(null);
    }

    public List<Employee> getFilteredEmployee(String gender, int yoj) {
        List<Employee> employees = employeeRepo.findAll();
        if(!gender.equalsIgnoreCase("A"))
            employees = employees.stream().filter(emp -> emp.getGender().equals(gender)).collect(Collectors.toList());

        if(yoj > 0)
            employees = employees.stream().filter(emp -> emp.getYoj() == yoj).collect(Collectors.toList());
        return employees;
    }
}
