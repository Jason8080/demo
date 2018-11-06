package com.gm.demo.shard.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Jason
 */
@Data
@Entity
@Table(name = "test_")
@ApiModel("掩饰实体")
public class Test implements Serializable {
    @Id
    @ApiModelProperty(hidden = true)
    private Long orderId;

    @ApiModelProperty(hidden = true)
    private Long userId;

    @ApiModelProperty(example = "嗯哼", value = "名称")
    private String name;
}