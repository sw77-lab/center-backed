package com.learn.model.domain.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 *
 * 用户注册请求体
 *
 * @author 邵雯
 */
@Data
public class UserRegisterRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 863682914249946402L;

    private String userAccount;
    private String userPassword;
    private String checkPassword;
    private String planetCode;
}
