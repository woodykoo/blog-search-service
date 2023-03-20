package com.woody.webservice.exception;

public enum ErrorCode {

    INTERNAL_SERVER_ERROR(500, "서버 내부에 오류가 발생했습니다."),
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
