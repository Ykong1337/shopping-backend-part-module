package com.ftg.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ftg.demo.entity.GoodClassify;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface GoodClassifyMapper extends BaseMapper<GoodClassify> {

    @Insert("insert into good_classify(pid,name,state) values(#{arg0},#{arg1},'使用中')")
    void insertGoodClassify(Integer pid, String name);

    //差是否被弃用
    @Select("select state from good_classify where pid = #{arg0} and name = #{arg1}")
    String selectState(Integer pid, String name);

    //手动启用
    @Update("update good_classify set state = '使用中' where pid = #{arg0} and name = #{arg1}")
    void enableState(Integer pid, String name);

    @Update("update good_classify set state = '已弃用' where pid = #{arg0} and name = #{arg1}")
    void deleteGood(Integer pid, String name);

    //改类别名
    @Update("update good_classify set name = #{arg2} where pid = #{arg0} and name = #{arg1}")
    void updateGood(Integer pid, String name, String new_name);
}
