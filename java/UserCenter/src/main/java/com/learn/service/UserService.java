package com.learn.service;

import com.learn.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author 邵雯
* @description 针对表【user】的数据库操作Service
* @createDate 2024-12-20 11:08:34
*/
public interface UserService extends IService<User> {
    /**
     * 用户注册
     * @param userAccount 用户账号
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 注册后的id
     */
    long userRegister(String userAccount,String userPassword,String checkPassword,String planetCode);

    /**
     * 脱敏函数
     * @param user 用户类
     * @return 脱敏后的用户类
     */
    User getSaftyUser(User user);

    /**
     *
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @param request session标识符
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     *
     * @param request session标识符
     * @return 返回一个整数
     */
    int userLogout(HttpServletRequest request);
}
