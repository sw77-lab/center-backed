package com.learn.constant;

public interface UserConstant {
    //用户登录状态键
    String USER_LOGIN_STATE = "userLoginState";
    //管理员权限
    int ADMIN_ROLE = 1;
    //默认权限
    int DEFAULT_ROLE = 0;
    //验证账户特殊符号的正则表达式
    String VALID_PATTERN = "\\pP|\\pS|\\s+";
    //加密的盐数据
    String SALT = "sHaoWen";

    //正常状态码
    Integer OK = 00000;
}
