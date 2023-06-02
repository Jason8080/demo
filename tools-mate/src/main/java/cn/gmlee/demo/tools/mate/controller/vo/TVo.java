package cn.gmlee.demo.tools.mate.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jas°
 * @since 2023-06-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="T对象", description="")
public class TVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;

    /**
     * 环境
     */
    @ApiModelProperty(value = "环境")
    private String env;

    /**
     * 状态值
     */
    @ApiModelProperty(value = "状态值")
    private Integer status;

    /**
     * 状态名
     */
    @ApiModelProperty(value = "状态名")
    private String state;

    /**
     * 创建人名
     */
    @ApiModelProperty(value = "创建人名")
    private String creator;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createAt;

    /**
     * 更新人名
     */
    @ApiModelProperty(value = "更新人名")
    private String updater;

    /**
     * 更新人ID
     */
    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateAt;

    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    private Boolean del;

    /**
     * 删除标记
     */
    @ApiModelProperty(value = "删除标记")
    private String delMark;


}
