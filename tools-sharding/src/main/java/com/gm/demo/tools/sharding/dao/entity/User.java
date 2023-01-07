package com.gm.demo.tools.sharding.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jas°
 * @since 2023-01-07
 */
@Data
@TableName("user")
public class User implements Serializable {
    private Long id;
    private String name;
    private Boolean gender;
    private Date birthday;
}
