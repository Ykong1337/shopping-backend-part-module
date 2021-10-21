package com.ftg.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ftg.demo.entity.SexEnum;
import com.ftg.demo.entity.User;
import com.ftg.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IService<User> {

    @Autowired
    UserMapper userMapper;

    public Object saveUser(User user) {
        return userMapper.insert(user);
    }

    //密码对照
    public String selectPassword(User user) {
        String acc = null;
        if (user.getAccount() != null) {
            acc = user.getAccount();
        } else if (user.getPhone_number() != null) {
            acc = user.getPhone_number();
        } else if (user.getMail() != null) {
            acc = user.getMail();
        }
        return userMapper.selectPassword(acc);
    }

    //手机号对照
    public String selectPhone(User user) {
        String acc = null;
        if (user.getAccount() != null) {
            acc = user.getAccount();
        } else if (user.getPhone_number() != null) {
            acc = user.getPhone_number();
        } else if (user.getMail() != null) {
            acc = user.getMail();
        }
        return userMapper.selectPhone(acc);
    }

    public Object updatePassword(User user) {
        String new_password = user.getNew_password();
        String phone_number = user.getPhone_number();
        return userMapper.updatePassword(new_password, phone_number);
    }

    public int add_information(String username, String phone_number, String mail, String account, String hometown, SexEnum sex, Date birth) {
        return userMapper.add_information(username, phone_number, mail, account, hometown, sex, birth);
    }

}
