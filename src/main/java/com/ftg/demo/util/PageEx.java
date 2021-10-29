package com.ftg.demo.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public class PageEx<T> extends Page {

    private final String  DEFAULT_MSG ="成功";

    int code;

    String msg;

    public PageEx(){
        this.code = 0;
        this.msg = DEFAULT_MSG;
    }

    public PageEx(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public PageEx(int page,int limit){
        super(page,limit);
    }


    List<T> data;

    public List<T> getData() {
        return super.getRecords();
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
