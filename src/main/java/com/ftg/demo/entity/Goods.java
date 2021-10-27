package com.ftg.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {

    @TableId
    private Integer id;

    private Integer gid;

    private String gname;

    private GoodsStateEnum state;

    private String type;

    private GoodsHeatEnum heat;
}
