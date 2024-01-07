package com.saranga.employee_manage.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RequestEmployeeDto {

    private Long id;
    private String name;
    private String address;
    private String position;
    private Double salary;

}
