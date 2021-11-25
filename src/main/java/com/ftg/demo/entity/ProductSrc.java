package com.ftg.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSrc {

    @TableId
    private Integer id;

    private String srcname;

    private String srcaddress;

    private String master;

    private String srcdesc;
}
