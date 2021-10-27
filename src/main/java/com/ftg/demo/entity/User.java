package com.ftg.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @TableId
    private Integer id;

    private String username;

    private Date birth;

    private UserSexEnum sex;

    private String hometown;

    private String account;

    private String phone_number;

    private String mail;

    private String password;
}
