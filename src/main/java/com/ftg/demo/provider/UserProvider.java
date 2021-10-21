package com.ftg.demo.provider;

import com.ftg.demo.entity.SexEnum;
import org.apache.ibatis.jdbc.SQL;

import java.util.Date;

public class UserProvider {

    public String updatePro(String username, String phone_number, String mail, String account, String hometown, SexEnum sex, Date birth) {

        return new SQL() {
            {
                UPDATE("user");

                if (username != null) {
                    SET("username = #{arg0}");
                }
                if (mail != null) {
                    SET("mail = #{arg2}");
                }
                if (account != null) {
                    SET("account = #{arg3}");
                }
                if (hometown != null) {
                    SET("hometown = #{arg4}");
                }
                if (sex != null) {
                    SET("sex = #{arg5}");
                }
                if (birth != null) {
                    SET("birth = #{arg6}");
                }
                WHERE("phone_number = #{arg1}");
            }
        }.toString();
    }
}
