package com.ftg.demo.controller;

import com.ftg.demo.entity.SexEnum;
import com.ftg.demo.entity.User;
import com.ftg.demo.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public Object reg(User user) {

        //判断手机号是否11位，判断密码长度是否>=6，是否<=12
        if (user.getPhone_number().length() != 11 || user.getPassword().length() > 12 || user.getPassword().length() < 6) {

            return "注册失败，请检查信息";

        } else {

            //判断是否为数字
            for (int i = 0; i < 11; i++) {
                if (user.getPhone_number().charAt(i) > 57 || user.getPhone_number().charAt(i) < 48) {
                    return "注册失败,请检查信息";
                }
            }

            return userService.saveUser(user);
        }
    }

    @PostMapping("/login")
    public Object login(User user) {

        if (user.getPassword().equals(userService.selectPassword(user))) {
            return "登陆成功";
        } else {
            return "登陆失败";
        }
    }

    @PostMapping("/updatePassword")
    public Object update(User user) {

        if (user.getPassword().equals(userService.selectPassword(user)) && user.getPhone_number().equals(userService.selectPhone(user))) {
            if (user.getNew_password().length() > 12 || user.getNew_password().length() < 6) {
                return "修改失败，请检查信息";
            } else {
                return userService.updatePassword(user);
            }
        } else {
            return "信息不正确";
        }

    }

    @PostMapping("/add_information")
    public Object add_information(User user) {

        String username = user.getUsername();
        String phone_number = user.getPhone_number();
        String mail = user.getMail();
        String account = user.getAccount();
        String hometown = user.getHometown();
        SexEnum sex = user.getSex();
        Date birth = user.getBirth();

        if (phone_number != null) {
            return userService.add_information(username, phone_number, mail, account, hometown, sex, birth);
        } else {
            return "添加失败，请输入手机号";
        }
    }

}
