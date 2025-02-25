package com.learn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.common.ErrorCode;
import com.learn.exception.BusinessException;
import com.learn.model.domain.User;
import com.learn.service.UserService;
import com.learn.mapper.UserMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.javassist.compiler.ast.Variable;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.learn.constant.UserConstant.*;

/**
* @author 邵雯
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-12-20 11:08:34
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private UserMapper userMapper;

    /**
     * 校验账户和密码函数
     */
    public boolean validate(String userAccount, String userPassword){
        //判断非空
        boolean result = StringUtils.isAnyBlank(userAccount,userPassword);
        if(result){
            return false;
        }
        //用户账号长度大于等于4
        if(userAccount.length() < 4){
            return false;
        }
        //用户密码长度大于等于8
        if(userPassword.length() < 8){
            return false;
        }
        //用户账户不可包含特殊字符
        Matcher matcher = Pattern.compile(VALID_PATTERN).matcher(userAccount);
        if(matcher.find()){
            return false;
        }
        return true;
    }
    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword,String planetCode) {
        //校验账户和密码
        boolean resultValidate = validate(userAccount,userPassword);
        if(!resultValidate){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账户/密码不合理");
        }
        //用户账户唯一不可重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if(count > 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账户已存在");
        }
        //星球编号唯一不可重复
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("planetCode",planetCode);
        count = userMapper.selectCount(queryWrapper);
        if(count > 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"星球编号已存在");
        }
        //仅仅需判断校验密码、星球编号非空
        boolean result = StringUtils.isAnyBlank(checkPassword,planetCode);
        if(result){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"确认密码与密码不统一/星球编号为空");
        }
        //校验密码大于等于8位,星球编号小于等于5位
        if(checkPassword.length() < 8 || planetCode.length() > 5){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"确认密码/星球编号不合理");
        }
        //用户密码加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        //插入数据库
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        user.setPlanetCode(planetCode);
        boolean saveResult = this.save(user);
        if(!saveResult){
            return -1;
        }
        return user.getID();
    }

    @Override
    public User getSaftyUser(User user){
        //返回用户数据脱敏
        User saftyUser = new User();
        saftyUser.setID(user.getID());
        saftyUser.setUsername(user.getUsername());
        saftyUser.setUserAccount(user.getUserAccount());
        saftyUser.setPlanetCode(user.getPlanetCode());
        saftyUser.setAvatarUrl(user.getAvatarUrl());
        saftyUser.setGender(user.getGender());
        saftyUser.setPhone(user.getPhone());
        saftyUser.setEmail(user.getEmail());
        saftyUser.setUserStatus(user.getUserStatus());
        saftyUser.setCreateTime(user.getCreateTime());
        saftyUser.setUserRole(user.getUserRole());
        return saftyUser;
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        //校验账户和密码
        boolean resultValidate = validate(userAccount,userPassword);
        if(!resultValidate){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账户/密码不合理");
        }
        //用户密码加密,因为数据库存的时候就是存的加密后的密码
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        //查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        queryWrapper.eq("userPassword",encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        //用户不存在
        if(user == null){
            log.info("user login failed, userAccount Cannot match userPassword");
            return null;
        }
        User saftyUser = getSaftyUser(user);
        //记录用户登录态
        request.getSession().setAttribute(USER_LOGIN_STATE,saftyUser);
        return saftyUser;
    }

    @Override
    public int userLogout(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return 1;
    }
}




