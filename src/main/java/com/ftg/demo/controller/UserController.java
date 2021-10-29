package com.ftg.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ftg.demo.entity.UserSexEnum;
import com.ftg.demo.entity.User;
import com.ftg.demo.service.UserService;
import com.ftg.demo.util.PageEx;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@Api(tags = "用户管理")
public class UserController {

    @Resource
    UserService userService;

    @GetMapping("/pageUser")
    @ApiOperation("分页查询")
    public PageEx<User> hello1(@RequestParam int page, @RequestParam int limit) {
        return userService.showPage(page, limit);
    }

    @GetMapping("/showAll")
    public Page<User> shwoAll(@RequestParam int page, @RequestParam int limit) {
        return userService.showAll(page, limit);
    }

    @PostMapping("/register")
    public Object reg(@RequestParam String phone_number, @RequestParam String password) {
        return userService.registerUser(phone_number, password);
    }

    @PostMapping("/login")
    public Object login(@RequestParam String account, @RequestParam String password) {
        return userService.checkPassword(account, password);
    }

    @PostMapping("/updatePassword")
    public Object update(@RequestParam String account, @RequestParam String password) {
        return userService.modifyUserPassword(account, password);
    }

    @PostMapping("/add_information")
    public Object add_information(@RequestParam String phone_number, @RequestParam String username, @RequestParam Date birth, @RequestParam UserSexEnum sex, @RequestParam String hometown, @RequestParam String account, @RequestParam String mail) {
        return userService.modifyUserInfo(phone_number, username, birth, sex, hometown, account, mail);
    }

}
