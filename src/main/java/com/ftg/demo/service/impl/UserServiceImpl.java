package com.ftg.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ftg.demo.entity.UserSexEnum;
import com.ftg.demo.entity.User;
import com.ftg.demo.mapper.UserMapper;
import com.ftg.demo.service.UserService;
import com.ftg.demo.util.PageEx;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    Map<String, Object> map = new HashMap<>();

    @Override
    public PageEx<User> showPage(int page, int limit) {
        PageEx<User> ip = new PageEx<>(page, limit);
        userMapper.selectPage(ip, null);
        return ip;
    }

    @Override
    public Page<User> showAll(int page, int limit) {
        Page<User> page1 = new Page<>(page, limit);
        userMapper.selectPage(page1, null);
        return page1;
    }

    @Override
    public Map<String, Object> checkPassword(String account, String password) {
        map.clear();
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("account", account);
        qw.or();
        qw.eq("mail", account);
        qw.or();
        qw.eq("phone_number", account);

        User user = userMapper.selectOne(qw);
        if (user == null) {
            map.put("code", 502);
            map.put("msg", "账号不存在！");
        } else {
            map.put("code", user.getPassword().equals(password) ? 200 : 502);
            map.put("msg", user.getPassword().equals(password) ? "登陆成功！" : "密码错误");
            map.put("data", user);
        }
        return map;
    }

    @Override
    public Map<String, Object> registerUser(String phone_number, String password) {
        map.clear();
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("phone_number", phone_number);
        User user = userMapper.selectOne(query);
        if (user == null) {
            //password 没有加密呢?
            map.put("code", 200);
            map.put("msg", "注册成功！请登陆！！");
            map.put("data", 1);
            userMapper.registerUser(phone_number, password);
        } else {
            map.put("code", 501);
            map.put("msg", "当前手机号已注册，请直接登陆！！！");
            map.put("data", 0);
        }
        return map;
    }

    @Override
    public Map<String, Object> modifyUserInfo(String phone_number, String username, Date birth, UserSexEnum sex, String hometown, String account, String mail) {
        map.clear();
        int n = userMapper.add_information(phone_number, username, birth, sex, hometown, account, mail);
        if (n > 0) {
            map.put("code", 200);
            map.put("msg", "信息添加成功！！！");
            map.put("data", 1);
        } else {
            map.put("code", 503);
            map.put("msg", "失败！！！");
        }
        return map;
    }

    @Override
    public Map<String, Object> modifyUserPassword(String account, String password) {
        map.clear();
        int n = userMapper.modifyUser(account, password);
        if (n > 0) {
            map.put("code", 200);
            map.put("msg", "个人密码修改成功！！！");
            map.put("data", 1);
        } else {
            map.put("code", 503);
            map.put("msg", "失败！！！");
        }
        return map;
    }
}
