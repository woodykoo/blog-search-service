package com.woody.client.naver.exception;

/**
 * Created by woody 2023.03.22
 * 네이버 서버에러 Exception
 * */
public class NaverServerErrorException extends NaverClientException {
    public NaverServerErrorException(NaverErrorResponse errorResponse) {
        super(errorResponse);
    }
}
