package com.ftg.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ftg.demo.entity.GoodClassify;
import com.ftg.demo.util.PageEx;

import java.util.Map;

public interface GoodClassifyService {

    Page<GoodClassify> showAll(int page, int limit);

    PageEx<GoodClassify> fuzzyQuery(int page, int limit, String name);

    Map<String, Object> insertGoodClassify(Integer pid, String name);

    Map<String, Object> deleteGoodClassify(Integer pid, String name);

    Map<String, Object> updateGoodClassifyName(Integer pid, String name, String new_name);

    PageEx<GoodClassify> showPage(int page, int limit);
}
