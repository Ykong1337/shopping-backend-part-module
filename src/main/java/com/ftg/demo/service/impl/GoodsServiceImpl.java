package com.ftg.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ftg.demo.entity.Goods;
import com.ftg.demo.entity.GoodsHeatEnum;
import com.ftg.demo.entity.GoodsStateEnum;
import com.ftg.demo.mapper.GoodsMapper;
import com.ftg.demo.service.GoodsService;
import com.ftg.demo.util.PageEx;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    GoodsMapper goodsMapper;

    Map<String, Object> map = new HashMap<>();

    @Override
    public PageEx<Goods> fuzzyQuery(int page, int limit, String gname) {
        PageEx<Goods> ip = new PageEx<>(page, limit);
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("gname", gname);
        goodsMapper.selectPage(ip, queryWrapper);
        return ip;
    }

    @Override
    public PageEx<Goods> showPage(int page, int limit) {
        PageEx<Goods> ip = new PageEx<>(page, limit);
        goodsMapper.selectPage(ip, null);
        return ip;
    }

    @Override
    public Page<Goods> showAll(int page, int limit) {
        Page<Goods> page1 = new Page<>(page, limit);
        goodsMapper.selectPage(page1, null);
        return page1;
    }

    @Override
    public Page<Goods> showOnLine(int page, int limit, GoodsStateEnum state, GoodsHeatEnum heat) {
        Page<Goods> page1 = new Page<>(page, limit);
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state", state);
        queryWrapper.eq("heat", heat);
        goodsMapper.selectPage(page1, queryWrapper);
        return page1;
    }

    @Override
    public Map<String, Object> insertGood(Integer gid, String gname, GoodsStateEnum state, String type) {
        map.clear();
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("gid", gid);
        queryWrapper.eq("gname", gname);
        Goods goods = goodsMapper.selectOne(queryWrapper);

        if (goods == null) {
            if (goodsMapper.selectGoodClassifyState(gid, type).equals("已弃用")) {
                goodsMapper.enableGoodClassifyState(gid, type);
            }
            map.put("code", 200);
            map.put("msg", "添加成功");
            map.put("data", 1);
            goodsMapper.insertGood(gid, gname, state, type);
        } else {
            if (goodsMapper.selectGoodState(gid, gname).equals("已下架")) {
                map.put("code", 200);
                map.put("msg", "已存在，手动上架");
                map.put("data", 1);
                goodsMapper.enableGoodState(gid, gname);
            } else {
                map.put("code", 503);
                map.put("msg", "已存在并已启用");
            }
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteGood(Integer gid, String gname) {
        map.clear();
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("gid", gid);
        queryWrapper.eq("gname", gname);
        Goods goods = goodsMapper.selectOne(queryWrapper);
        if (goods != null) {
            map.put("code", 200);
            map.put("msg", "下架成功");
            map.put("data", 1);
            goodsMapper.downGoodState(gid, gname);
        } else {
            map.put("code", 503);
            map.put("msg", "找不到商品");
        }
        return map;
    }

    @Override
    public Map<String, Object> updateGoodName(Integer gid, String gname, String new_gname) {
        map.clear();
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("gid", gid);
        queryWrapper.eq("gname", gname);
        Goods goods = goodsMapper.selectOne(queryWrapper);
        if (goods != null) {
            map.put("code", 200);
            map.put("msg", "修改成功");
            map.put("data", 1);
            goodsMapper.updateGoodName(gid, gname, new_gname);
        } else {
            map.put("code", 503);
            map.put("msg", "找不到类别");
        }

        return map;
    }

    @Override
    public Map<String, Object> updateGoodHeat(Integer gid, String gname, GoodsHeatEnum heat) {
        map.clear();
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("gid", gid);
        queryWrapper.eq("gname", gname);
        Goods goods = goodsMapper.selectOne(queryWrapper);
        if (goods != null) {
            map.put("code", 200);
            map.put("msg", "修改成功");
            map.put("data", 1);
            goodsMapper.updateGoodHeat(gid, gname, heat);
        } else {
            map.put("code", 503);
            map.put("msg", "找不到类别");
        }

        return map;
    }

    @Override
    public Map<String, Object> updateGoodState(Integer gid, String gname, GoodsStateEnum state) {
        map.clear();
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("gid", gid);
        queryWrapper.eq("gname", gname);
        Goods goods = goodsMapper.selectOne(queryWrapper);
        if (goods != null) {
            map.put("code", 200);
            map.put("msg", "修改成功");
            map.put("data", 1);
            goodsMapper.updateGoodState(gid, gname, state);
        } else {
            map.put("code", 503);
            map.put("msg", "找不到类别");
        }
        return map;
    }
}
