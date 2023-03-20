package com.woody.client.naver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NaverErrorResponse {
    private String errorCode;
    private String errorMessage;
}
