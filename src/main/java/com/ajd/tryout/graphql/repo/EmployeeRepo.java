package com.ajd.tryout.graphql.repo;

import com.ajd.tryout.graphql.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    // Employee findById(Long id);
}
