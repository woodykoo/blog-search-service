package com.woody.client.naver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by woody 2023.03.22
 * 네이버 에러 응답 DTO
 * */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NaverErrorResponse {
    private String errorCode;
    private String errorMessage;
}
