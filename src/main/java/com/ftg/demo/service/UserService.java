package com.ftg.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ftg.demo.entity.SexEnum;
import com.ftg.demo.entity.User;
import com.ftg.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

public interface UserService {

    Page<User> showAll(int page, int limit);


    Map<String, Object> checkPassword(String account, String password);


    Map<String, Object> registerUser(String phone_number, String password);


    Map<String, Object> modifyUserInfo(String phone_number, String username, Date birth, SexEnum sex, String hometown, String account, String mail);


    Map<String, Object> modifyUserPassword(String account, String password);

}
