package com.saranga.employee_manage.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public class CommonResponse {
    public final Object data;
    public final String message;
    public final HttpStatus responseCode;
}
