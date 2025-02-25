package com.learn.service;
import java.util.Date;

import com.learn.model.domain.User;
import com.learn.model.domain.request.UserLoginRequest;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {
    @Resource
    private UserService userService ;
    @Test
    void testAddUser(){
        User user = new User();
        user.setUsername("sHao");
        user.setUserAccount("7777");
        user.setAvatarUrl("https://c-ssl.duitang.com/uploads/blog/202012/19/20201219175131_19c0c.jpg");
        user.setGender(0);
        user.setUserPassword("1234");
        user.setPhone("123");
        user.setEmail("123");

        boolean result = userService.save(user);
        System.out.println(user.getID());
        Assertions.assertTrue(result);
    }

    @Test
    void isEmpty() {
    }

//    @Test
//    void userRegister() {
//        String userAccount = "wWen";
//        String userPassword = "12345678";
//        String checkPassword = "12345678";
//        String planetCode = "";
//        long result = userService.userRegister(userAccount,userPassword,checkPassword,planetCode);
//        Assertions.assertEquals(-1,result);
//        planetCode = "123456";
//        result = userService.userRegister(userAccount,userPassword,checkPassword,planetCode);
//        Assertions.assertEquals(-1,result);
//        planetCode = "12345";
//        result = userService.userRegister(userAccount,userPassword,checkPassword,planetCode);
//        Assertions.assertTrue(result > 0);
//        userAccount = "SWen";
//        userPassword = "12345678";
//        checkPassword = "12345678";
//        planetCode = "12345";
//        result = userService.userRegister(userAccount,userPassword,checkPassword,planetCode);
//        Assertions.assertEquals(-1,result);
//        String planetCode = "9506";
//        //账户为空
//        String userAccount = "";
//        String userPassword = "12345678";
//        String checkPassword = "12345678";
//        long result = userService.userRegister(userAccount,userPassword,checkPassword,planetCode);
//        Assertions.assertEquals(-1,result);
//        //密码为空
//        userAccount = "sHaoWen";
//        userPassword = "";
//        result = userService.userRegister(userAccount,userPassword,checkPassword,planetCode);
//        Assertions.assertEquals(-1,result);
//        //账户小于4位
//        userAccount = "sh";
//        userPassword = "12345678";
//        result = userService.userRegister(userAccount,userPassword,checkPassword,planetCode);
//        Assertions.assertEquals(-2,result);
//        //密码小于8位
//        userAccount = "sHaoWen";
//        userPassword = "123456";
//        result = userService.userRegister(userAccount,userPassword,checkPassword,planetCode);
//        Assertions.assertEquals(-2,result);
//        //密码与校验密码不等
//        userAccount = "sHaoWen";
//        userPassword = "12345679";
//        result = userService.userRegister(userAccount,userPassword,checkPassword,planetCode);
//        Assertions.assertEquals(-3,result);
//        //账户重复
//        userAccount = "sHao";
//        userPassword = "12345678";
//        result = userService.userRegister(userAccount,userPassword,checkPassword,planetCode);
//        Assertions.assertEquals(-4,result);
//        //账户包含特殊字符
//        userAccount = "sHao#";
//        result = userService.userRegister(userAccount,userPassword,checkPassword,planetCode);
//        Assertions.assertEquals(-5,result);
//        //成功注册
//        userAccount = "sHaoWen";
//        result = userService.userRegister(userAccount,userPassword,checkPassword,planetCode);
//        Assertions.assertTrue(result > 0);
//    }
}