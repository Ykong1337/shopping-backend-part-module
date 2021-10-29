package com.ftg.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ftg.demo.entity.GoodClassify;
import com.ftg.demo.mapper.GoodClassifyMapper;
import com.ftg.demo.service.GoodClassifyService;
import com.ftg.demo.util.PageEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GoodClassifyServiceImpl implements GoodClassifyService {

    @Autowired
    GoodClassifyMapper goodClassifyMapper;

    @Override
    public Page<GoodClassify> showAll(int page, int limit) {
        Page<GoodClassify> page1 = new Page<>(page, limit);
        goodClassifyMapper.selectPage(page1, null);
        return page1;
    }

    @Override
    public PageEx<GoodClassify> showPage(int page, int limit) {
        PageEx<GoodClassify> ip = new PageEx<>(page,limit);
        return goodClassifyMapper.selectPage(ip,null);
    }

    @Override
    public Map<String, Object> insertGoodClassify(Integer pid, String name) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<GoodClassify> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", pid);
        queryWrapper.eq("name", name);
        GoodClassify goodClassify = goodClassifyMapper.selectOne(queryWrapper);

        if (goodClassify == null) {
            map.put("code", 200);
            map.put("msg", "添加成功");
            map.put("data", 1);
            goodClassifyMapper.insertGoodClassify(pid, name);
        } else {
            if (goodClassifyMapper.selectState(pid,name).equals("已弃用")){
                map.put("code", 200);
                map.put("msg", "已存在，手动启用");
                map.put("data", 1);
                goodClassifyMapper.enableState(pid, name);
            } else {
                map.put("code", 503);
                map.put("msg", "已存在并已启用");
            }
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteGoodClassify(Integer pid, String name) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<GoodClassify> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", pid);
        queryWrapper.eq("name", name);
        GoodClassify goodClassify = goodClassifyMapper.selectOne(queryWrapper);
        if (goodClassify != null) {
            map.put("code", 200);
            map.put("msg", "弃用成功");
            map.put("data", 1);
            goodClassifyMapper.deleteGood(pid, name);
        } else {
            map.put("code", 503);
            map.put("msg", "找不到类别");
        }
        return map;
    }

    @Override
    public Map<String, Object> updateGoodClassifyName(Integer pid, String name, String new_name) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<GoodClassify> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", pid);
        queryWrapper.eq("name", name);
        GoodClassify goodClassify = goodClassifyMapper.selectOne(queryWrapper);
        if (goodClassify != null){
            map.put("code", 200);
            map.put("msg", "修改成功");
            map.put("data", 1);
            goodClassifyMapper.updateGood(pid, name, new_name);
        } else {
            map.put("code", 503);
            map.put("msg", "找不到类别");
        }
        return map;
    }



}
