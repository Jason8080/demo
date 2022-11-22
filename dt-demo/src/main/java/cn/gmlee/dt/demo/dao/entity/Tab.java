package cn.gmlee.dt.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jas°
 * @since 2022-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tab")
@ApiModel(value="Tab对象", description="")
public class Tab implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 状态名
     */
    @ApiModelProperty(value = "状态名")
    private String state;

    /**
     * 创建人编号
     */
    @ApiModelProperty(value = "创建人编号")
    private Long createdBy;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称")
    private String creator;

    /**
     * 修改人编号
     */
    @ApiModelProperty(value = "修改人编号")
    private Long updatedBy;

    /**
     * 修改人名称
     */
    @ApiModelProperty(value = "修改人名称")
    private String updater;

    /**
     * 删除标识
     */
    @ApiModelProperty(value = "删除标识")
    private Boolean del;

    /**
     * 验收标记: 0是测试数据, 1是真实数据
     */
    @ApiModelProperty(value = "验收标记: 0是测试数据, 1是真实数据")
    private Boolean mark;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updatedAt;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;


}
