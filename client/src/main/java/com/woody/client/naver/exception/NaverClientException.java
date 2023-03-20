package com.woody.client.naver.exception;

abstract class NaverClientException extends RuntimeException {

    private final NaverErrorResponse errorResponse;

    protected NaverClientException(NaverErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public NaverErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
