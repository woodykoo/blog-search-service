package com.woody.client.naver.exception;

public class NaverBadRequestException extends NaverClientException {

    public NaverBadRequestException(NaverErrorResponse errorResponse) {
        super(errorResponse);
    }
}
