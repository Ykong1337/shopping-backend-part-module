package com.ftg.demo.provider;

import com.ftg.demo.entity.UserSexEnum;
import org.apache.ibatis.jdbc.SQL;

import java.util.Date;

public class UserProvider {

    public String updatePro(String phone_number, String username, Date birth, UserSexEnum sex, String hometown, String account, String mail) {

        return new SQL() {
            {
                UPDATE("user");

                if (username != null) {
                    SET("username = #{arg1}");
                }
                if (birth != null) {
                    SET("birth = #{arg2}");
                }
                if (sex != null) {
                    SET("sex = #{arg3}");
                }
                if (hometown != null) {
                    SET("hometown = #{arg4}");
                }
                if (account != null) {
                    SET("account = #{arg5}");
                }
                if (mail != null) {
                    SET("mail = #{arg6}");
                }
                WHERE("phone_number = #{arg0}");
            }
        }.toString();
    }
}
