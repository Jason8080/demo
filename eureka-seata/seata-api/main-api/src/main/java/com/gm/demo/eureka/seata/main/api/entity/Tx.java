package com.gm.demo.eureka.seata.main.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Jas°
 * @date 2021/2/24 (周三)
 */
@Data
@ApiModel
@TableName("tx")
public class Tx {
    @ApiModelProperty
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty
    private String str;

    public Tx(String str) {
        this.str = str;
    }
}