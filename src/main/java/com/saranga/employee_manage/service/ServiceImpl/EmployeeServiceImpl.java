package com.saranga.employee_manage.service.ServiceImpl;

import com.saranga.employee_manage.dto.CommonResponse;
import com.saranga.employee_manage.dto.request.RequestEmployeeDto;
import com.saranga.employee_manage.entity.Employee;
import com.saranga.employee_manage.exception.AlreadyExistException;
import com.saranga.employee_manage.exception.NotInDatabaseException;
import com.saranga.employee_manage.repository.EmployeeRepo;
import com.saranga.employee_manage.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public ResponseEntity<CommonResponse> addEmployee(RequestEmployeeDto requestEmployeeDto) {
        try {
            log.info("get dto ServiceImpl : {} ", requestEmployeeDto);
            if (!employeeRepo.existsEmployeeByName(requestEmployeeDto.getName())) {

                employeeRepo.save(Employee.builder()
                        .name(requestEmployeeDto.getName())
                        .address(requestEmployeeDto.getAddress())
                        .position(requestEmployeeDto.getPosition())
                        .salary(requestEmployeeDto.getSalary())
                        .build());
                return new ResponseEntity<>(CommonResponse.builder()
                        .message("Successfully Saved !!")
                        .responseCode(HttpStatus.CREATED)
                        .build(), HttpStatus.CREATED);

            } else {

                throw new AlreadyExistException("Employee name already exist !!");
            }

        } catch (Exception e) {
            log.error("failed insert new employee {} Exception ", requestEmployeeDto.getName(), e);
            return new ResponseEntity<>(CommonResponse.builder()
                    .responseCode(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Error creating employee")
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<CommonResponse> getAllEmployee() {
        try {
            log.info("get all request come serviceImpl");
            List<RequestEmployeeDto> requestEmployeeDtoList = employeeRepo.findAll()
                    .stream()
                    .map(employee -> RequestEmployeeDto.builder()
                            .id(employee.getId())
                            .address(employee.getAddress())
                            .name(employee.getName())
                            .position(employee.getPosition())
                            .salary(employee.getSalary())
                            .build())
                    .toList();
            return ResponseEntity.ok(CommonResponse.builder()
                    .data(requestEmployeeDtoList)
                    .message("employee list")
                    .responseCode(HttpStatus.FOUND)
                    .build());
        }catch (Exception e){
            return new ResponseEntity<>(CommonResponse.builder()
                    .responseCode(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("get all employee failed!")
                    .build(),HttpStatus.INTERNAL_SERVER_ERROR);
        }




    }

    @Override
    public ResponseEntity<CommonResponse> updateEmployee(RequestEmployeeDto requestEmployeeDto) {

        try {
            log.info("serviceImpl update employee : {}", requestEmployeeDto.getName());

            Employee employee = employeeRepo.findById(requestEmployeeDto.getId()).get();
            employee.setAddress(requestEmployeeDto.getAddress());
            employee.setName(requestEmployeeDto.getName());
            employee.setPosition(requestEmployeeDto.getPosition());
            employee.setSalary(requestEmployeeDto.getSalary());
            employeeRepo.save(employee);

            return new ResponseEntity<>(CommonResponse.builder()
                    .message("update success !!")
                    .responseCode(HttpStatus.CREATED)
                    .build(), HttpStatus.CREATED);

        } catch (Exception e) {
            log.error("failed update employee {} Exception ", requestEmployeeDto.getName(), e);
            return new ResponseEntity<>(CommonResponse.builder()
                    .message("Failed to update employee")
                    .responseCode(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @Override
    public ResponseEntity<CommonResponse> deleteEmployee(Long id) {
        try {
            Employee employee = employeeRepo.findByIdEquals(id);
            if (employee != null){
                employeeRepo.deleteById(id);
                return new ResponseEntity<>(CommonResponse.builder()
                        .message("Employee delete successful !!")
                        .responseCode(HttpStatus.OK)
                        .build(),HttpStatus.OK);
            }
            else {
//                return new ResponseEntity<>(CommonResponse.builder()
//                        .message("this employee not in database !!")
//                        .responseCode(HttpStatus.NOT_FOUND)
//                        .build(),HttpStatus.NOT_FOUND );
                throw new NotInDatabaseException("Employee not in database");
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(CommonResponse.builder()
                    .responseCode(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Employee delete failed !!")
                    .build(),HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }
}
