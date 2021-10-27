package com.ftg.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ftg.demo.entity.UserSexEnum;
import com.ftg.demo.entity.User;
import com.ftg.demo.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/showAll")
    public Page<User> shwoAll(int page, int limit) {
        return userService.showAll(page, limit);
    }

    @PostMapping("/register")
    public Object reg(String phone_number, String password) {
        return userService.registerUser(phone_number, password);
    }

    @PostMapping("/login")
    public Object login(String account, String password) {
        return userService.checkPassword(account, password);
    }

    @PostMapping("/updatePassword")
    public Object update(String account, String password) {
        return userService.modifyUserPassword(account, password);
    }

    @PostMapping("/add_information")
    public Object add_information(String phone_number, String username, Date birth, UserSexEnum sex, String hometown, String account, String mail) {
        return userService.modifyUserInfo(phone_number, username, birth, sex, hometown, account, mail);
    }

}
