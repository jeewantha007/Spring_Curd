package com.saranga.employee_manage.controller;

import com.saranga.employee_manage.dto.CommonResponse;
import com.saranga.employee_manage.dto.request.RequestEmployeeDto;
import com.saranga.employee_manage.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/employee")
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/save")
    public ResponseEntity<CommonResponse> addEmployee(@RequestBody RequestEmployeeDto requestEmployeeDto) {
        log.info("client send : {}", requestEmployeeDto);
        return employeeService.addEmployee(requestEmployeeDto);
    }
    @GetMapping("/getAll")
    public ResponseEntity<CommonResponse> getAllEmployee(){
        log.info("Client request all data : Controller class log");
        return employeeService.getAllEmployee();
    }
    @PutMapping("/update")
    public ResponseEntity<CommonResponse> updateEmployee(@RequestBody RequestEmployeeDto requestEmployeeDto){
        log.info("client request update: Dto {}", requestEmployeeDto );
        return employeeService.updateEmployee(requestEmployeeDto);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CommonResponse> deleteEmployee(@PathVariable Long id){
        log.info("client id: {} request delete",id);
        return employeeService.deleteEmployee(id);

    }
}
