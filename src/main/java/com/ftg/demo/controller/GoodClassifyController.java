package com.ftg.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ftg.demo.entity.GoodClassify;
import com.ftg.demo.service.GoodClassifyService;
import com.ftg.demo.util.PageEx;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "商品类别管理")
public class GoodClassifyController {

    @Autowired
    GoodClassifyService goodClassifyService;

    @GetMapping("/pageGoodClassifyFuzzyQuery")
    @ApiOperation("模糊查询")
    public PageEx<GoodClassify> hello2(@RequestParam int page, @RequestParam int limit, @RequestParam String gname) {
        return goodClassifyService.fuzzyQuery(page, limit, gname);
    }

    @GetMapping("/pageGoodClassify")
    @ApiOperation("分页查询")
    public PageEx<GoodClassify> hello1(@RequestParam int page, @RequestParam int limit) {
        return goodClassifyService.showPage(page, limit);
    }

    @GetMapping("/showAllGoodClassify")
    @ApiOperation("列出全部商品类别信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "当前页码", name = "page"),
            @ApiImplicitParam(value = "多少条", name = "limit")
    })
    public Page<GoodClassify> shwoAll(int page, int limit) {
        return goodClassifyService.showAll(page, limit);
    }

    @PostMapping("/insertGoodClassify")
    @ApiOperation("添加商品类别")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "类别ID", name = "pid"),
            @ApiImplicitParam(value = "类别名字", name = "name")
    })
    public Object insert(Integer pid, String name) {
        return goodClassifyService.insertGoodClassify(pid, name);
    }

    @PostMapping("/deleteGoodClassify")
    @ApiOperation("弃用商品类别")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "类别ID", name = "pid"),
            @ApiImplicitParam(value = "类别名字", name = "name")
    })
    public Object delete(Integer pid, String name) {
        return goodClassifyService.deleteGoodClassify(pid, name);
    }

    @PostMapping("/updateGoodClassify")
    @ApiOperation("修改商品类别名")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "类别ID", name = "pid"),
            @ApiImplicitParam(value = "类别名字", name = "name"),
            @ApiImplicitParam(value = "新类别名字", name = "new_name")
    })
    public Object update(Integer pid, String name, String new_name) {
        return goodClassifyService.updateGoodClassifyName(pid, name, new_name);
    }

}
