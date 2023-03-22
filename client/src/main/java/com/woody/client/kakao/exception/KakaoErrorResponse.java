package com.woody.client.kakao.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by woody 2023.03.22
 * 카카오 에러 응답 DTO
 * */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KakaoErrorResponse {
    private String errorType;
    private String message;
}
