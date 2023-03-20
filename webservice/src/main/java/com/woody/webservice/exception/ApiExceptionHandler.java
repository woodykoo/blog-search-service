package com.woody.webservice.exception;

import com.woody.client.kakao.exception.KakaoBadRequestException;
import com.woody.client.kakao.exception.KakaoErrorResponse;
import com.woody.client.kakao.exception.KakaoServerErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleBindException(BindException e) {
        log.info("message : {}", e.getMessage());

        return ErrorResponse.of(e);
    }

    @ExceptionHandler(KakaoBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleKakaoBadRequestException(KakaoBadRequestException e) {
        KakaoErrorResponse errorResponse = e.getErrorResponse();
        log.error("KakaoBadRequestException : {}", errorResponse);

        return ErrorResponse.of(ErrorCode.BAD_REQUEST);
    }

    @ExceptionHandler(KakaoServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse handleKakaoServerErrorException(KakaoServerErrorException e) {
        KakaoErrorResponse errorResponse = e.getErrorResponse();
        log.error("KakaoServerErrorException : {}", errorResponse);

        return ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Exception : ", e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
