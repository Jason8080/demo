package cn.gmlee.test.mybatis.dao.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 城市字典表
 * </p>
 *
 * @author Jas°
 * @since 2021-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("c_city")
@ApiModel(value="City对象", description="城市字典表")
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "城市id")
    private Integer id;

    @ApiModelProperty(value = "城市英文名")
    private String nameEn;

    @ApiModelProperty(value = "城市中文名")
    private String nameCn;

    @ApiModelProperty(value = "所属省份中文名")
    private String provinceNameCn;

    @ApiModelProperty(value = "所属身份拼音")
    private String provinceNameEn;

    private BigDecimal latitude;

    private BigDecimal longitude;

    @ApiModelProperty(value = "是否允许下单:0-不允许,1-允许")
    private Integer enableOrder;

    @ApiModelProperty(value = "是否允许企业下单:0-不允许,1-允许")
    private Integer enableEorder;

    @ApiModelProperty(value = "版本号，控制缓存")
    private Integer revision;

    @ApiModelProperty(value = "城市是否开通会员")
    private Integer enableVip;

    @ApiModelProperty(value = "城市是否开通车贴")
    private Integer enableSticker;

    @ApiModelProperty(value = "是否允许注册:0-不允许,1-允许")
    private Integer enableRegister;

    @ApiModelProperty(value = "保证金")
    private Integer enableDeposit;

    @ApiModelProperty(value = "签署服务协议版本")
    private Integer protocolRevision;


}
