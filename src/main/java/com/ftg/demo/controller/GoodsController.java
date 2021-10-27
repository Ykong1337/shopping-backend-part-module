package com.ftg.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ftg.demo.entity.Goods;
import com.ftg.demo.entity.GoodsHeatEnum;
import com.ftg.demo.entity.GoodsStateEnum;
import com.ftg.demo.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "商品管理")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @GetMapping("/showAllGoods")
    @ApiOperation("显示所有商品")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "当前页码",name = "page"),
            @ApiImplicitParam(value = "多少条",name = "limit")
    })
    public Page<Goods> showAll(int page, int limit) {
        return goodsService.showAll(page, limit);
    }

    @GetMapping("/showAllOnLine")
    @ApiOperation("显示所有在售商品")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "当前页码",name = "page"),
            @ApiImplicitParam(value = "多少条",name = "limit"),
            @ApiImplicitParam(value = "筛选是否在售",name = "state"),
            @ApiImplicitParam(value = "筛选热度",name = "heat")
    })
    public Page<Goods> showAllOnLine(int page, int limit, GoodsStateEnum state, GoodsHeatEnum heat) {
        return goodsService.showOnLine(page, limit, state, heat);
    }

    @PostMapping("/insertGood")
    @ApiOperation("添加一条商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商品类别ID",name = "gid"),
            @ApiImplicitParam(value = "商品名",name = "gname"),
            @ApiImplicitParam(value = "选择是否在售",name = "state"),
            @ApiImplicitParam(value = "商品类别",name = "type")
    })
    public Object insert(Integer gid, String gname, GoodsStateEnum state, String type) {
        return goodsService.insertGood(gid, gname, state, type);
    }

    @PostMapping("/deleteGood")
    @ApiOperation("下架一条商品")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商品类别ID",name = "gid"),
            @ApiImplicitParam(value = "商品名",name = "gname")
    })
    public Object delete(Integer gid, String gname) {
        return goodsService.deleteGood(gid, gname);
    }

    @PostMapping("/updateGoodName")
    @ApiOperation("修改商品名字")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商品类别ID",name = "gid"),
            @ApiImplicitParam(value = "商品名",name = "gname"),
            @ApiImplicitParam(value = "新的商品名",name = "new_gname")
    })
    public Object updateGoodName(Integer gid, String gname, String new_gname) {
        return goodsService.updateGoodName(gid, gname, new_gname);
    }

    @PostMapping("/updateGoodHeat")
    @ApiOperation("修改商品热度")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商品类别ID",name = "gid"),
            @ApiImplicitParam(value = "商品名",name = "gname"),
            @ApiImplicitParam(value = "修改后的热度",name = "heat")
    })
    public Object updateGoodHeat(Integer gid, String gname, GoodsHeatEnum heat) {
        return goodsService.updateGoodHeat(gid, gname, heat);
    }
}
