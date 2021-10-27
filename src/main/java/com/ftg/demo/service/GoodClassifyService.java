package com.ftg.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ftg.demo.entity.GoodClassify;

import java.util.Map;

public interface GoodClassifyService {

    Page<GoodClassify> showAll(int page, int limit);

    Map<String, Object> insertGoodClassify(Integer pid, String name);

    Map<String, Object> deleteGoodClassify(Integer pid, String name);

    Map<String, Object> updateGoodClassifyName(Integer pid, String name, String new_name);
}
