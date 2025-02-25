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
public class UserLoginRequest implements Serializable {


    @Serial
    private static final long serialVersionUID = -1215879664552266855L;

    private String userAccount;
    private String userPassword;

}
