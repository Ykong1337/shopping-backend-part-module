package com.ftg.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ftg.demo.entity.SexEnum;
import com.ftg.demo.entity.User;
import com.ftg.demo.provider.UserProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.Date;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    //根据 手机号or账号or邮箱 取出密码
    @Select("select password from user where phone_number = #{acc} or account = #{acc} or mail = #{acc}")
    String selectPassword(String acc);

    //根据 手机号or账号or邮箱 取出手机号
    @Select("select phone_number from user where phone_number = #{acc} or account = #{acc} or mail = #{acc}")
    String selectPhone(String acc);

    @Update("update user set password = #{arg0} where phone_number = #{arg1}")
    int updatePassword(String new_password, String phone_number);

    @UpdateProvider(type = UserProvider.class, method = "updatePro")
    int add_information(String username, String phone_number, String mail, String account, String hometown, SexEnum sex, Date birth);

}
