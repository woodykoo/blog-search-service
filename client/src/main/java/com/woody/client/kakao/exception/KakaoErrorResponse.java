package com.woody.client.kakao.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KakaoErrorResponse {
    private String errorType;
    private String message;
}
