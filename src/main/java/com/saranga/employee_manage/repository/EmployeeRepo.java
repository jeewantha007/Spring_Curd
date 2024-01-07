package com.saranga.employee_manage.repository;

import com.saranga.employee_manage.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    boolean existsEmployeeByName(String name);

    Employee findByIdEquals(Long id);
}
