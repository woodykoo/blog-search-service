package com.woody.client.naver.exception;

public class NaverServerErrorException extends NaverClientException {

    public NaverServerErrorException(NaverErrorResponse errorResponse) {
        super(errorResponse);
    }
}
