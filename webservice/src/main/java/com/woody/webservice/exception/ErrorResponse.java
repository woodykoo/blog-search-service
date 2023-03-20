package com.woody.webservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindException;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String message;

    public static ErrorResponse of(BindException e) {
        return new ErrorResponse(e.getFieldError() != null ? e.getFieldError().getDefaultMessage() : "");
    }
}
