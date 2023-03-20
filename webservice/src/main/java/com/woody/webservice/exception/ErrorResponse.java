package com.woody.webservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String errorMessage;

    private int errorCode;

    public static ErrorResponse of(BindingResult bindingResult) {
        return new ErrorResponse(bindingResult.getFieldError() != null ? bindingResult.getFieldError().getDefaultMessage() : ErrorCode.BAD_REQUEST.getDesc(), ErrorCode.BAD_REQUEST.getCode());
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getDesc(), errorCode.getCode());
    }
}
