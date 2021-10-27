package com.ftg.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodClassify {

    @TableId
    private Integer id;

    private Integer pid;

    private String name;

    private GoodClassifyStateEnum state;
}
