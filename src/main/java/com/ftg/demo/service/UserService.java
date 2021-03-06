package com.ftg.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ftg.demo.entity.UserSexEnum;
import com.ftg.demo.entity.User;
import com.ftg.demo.util.PageEx;

import java.util.Date;
import java.util.Map;

public interface UserService {

    PageEx<User> showPage(int page, int limit);

    Page<User> showAll(int page, int limit);

    Map<String, Object> checkPassword(String account, String password);

    Map<String, Object> registerUser(String phone_number, String password);

    Map<String, Object> modifyUserInfo(String phone_number, String username, Date birth, UserSexEnum sex, String hometown, String account, String mail);

    Map<String, Object> modifyUserPassword(String account, String password);

}
