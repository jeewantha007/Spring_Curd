package com.saranga.employee_manage.service;

import com.saranga.employee_manage.dto.CommonResponse;
import com.saranga.employee_manage.dto.request.RequestEmployeeDto;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {
    ResponseEntity<CommonResponse> addEmployee(RequestEmployeeDto requestEmployeeDto);

    ResponseEntity<CommonResponse> getAllEmployee();

    ResponseEntity<CommonResponse> updateEmployee(RequestEmployeeDto requestEmployeeDto);

    ResponseEntity<CommonResponse> deleteEmployee(Long id);
}
