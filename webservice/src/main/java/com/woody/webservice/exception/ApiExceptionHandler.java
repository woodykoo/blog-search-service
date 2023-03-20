package com.woody.webservice.exception;

import com.woody.client.kakao.exception.KakaoBadRequestException;
import com.woody.client.kakao.exception.KakaoErrorResponse;
import com.woody.client.kakao.exception.KakaoServerErrorException;
import com.woody.client.naver.exception.NaverBadRequestException;
import com.woody.client.naver.exception.NaverErrorResponse;
import com.woody.client.naver.exception.NaverServerErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    @ExceptionHandler(NaverBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleNaverBadRequestException(NaverBadRequestException e) {
        NaverErrorResponse errorResponse = e.getErrorResponse();
        log.error("NaverBadRequestException : {}", errorResponse);

        return ErrorResponse.of(ErrorCode.BAD_REQUEST);
    }

    @ExceptionHandler(NaverServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse handleNaverServerErrorException(NaverServerErrorException e) {
        NaverErrorResponse errorResponse = e.getErrorResponse();
        log.error("NaverServerErrorException : {}", errorResponse);

        return ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse handleException(Exception e) {
        log.error("Exception : ", e);

        return ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
