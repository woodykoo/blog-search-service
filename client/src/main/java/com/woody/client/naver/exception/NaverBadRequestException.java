package com.woody.client.naver.exception;

/**
 * Created by woody 2023.03.22
 * 4xx 에러 Exception
 * */
public class NaverBadRequestException extends NaverClientException {
    public NaverBadRequestException(NaverErrorResponse errorResponse) {
        super(errorResponse);
    }
}
