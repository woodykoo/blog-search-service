package com.woody.webservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindException;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String errorMessage;

    private int errorCode;

    public static ErrorResponse of(BindException e) {
        return new ErrorResponse(e.getFieldError() != null ? e.getFieldError().getDefaultMessage() : ErrorCode.BAD_REQUEST.getDesc(), ErrorCode.BAD_REQUEST.getCode());
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getDesc(), errorCode.getCode());
    }
}
