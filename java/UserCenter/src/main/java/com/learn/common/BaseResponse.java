package com.learn.common;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 统一返回类，作为统一的函数返回类型
 * @param <T> 泛型
 * @author 邵雯
 */
@Data
public class BaseResponse<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 2201393220016099719L;

    private int code;
    private T data;
    private String message;
    private String desciption;

    public BaseResponse(int code,T data,String message,String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.desciption = description;
    }

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(),null,errorCode.getMessage(),errorCode.getDescription());
    }
}
