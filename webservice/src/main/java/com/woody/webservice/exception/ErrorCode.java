package com.woody.webservice.exception;

public enum ErrorCode {

    INTERNAL_SERVER_ERROR(500, "서버 에러가 발생 했습니다."),
    BAD_REQUEST(400, "잘못된 요청입니다.");

    ErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
