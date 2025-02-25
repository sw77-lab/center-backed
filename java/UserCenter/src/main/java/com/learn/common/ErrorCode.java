package com.learn.common;

/**
 * 错误码
 *
 * @author 邵雯
 */

public enum ErrorCode {
    SUCCESS(0,"ok",""),
    PARAMS_ERROR(40000,"请求参数错误",""),
    NULL_ERROR(40001,"请求数据错误",""),
    NOT_LOGIN(40100,"未登录",""),
    NO_AUTH(40101,"无权限",""),
    SYSTEM_ERROR(50000,"系统内部异常","");

    private final int code;
    private final String message;
    private final String description;

    ErrorCode(int code, String description, String message) {
        this.code = code;
        this.description = description;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getMessage() {
        return message;
    }
}
