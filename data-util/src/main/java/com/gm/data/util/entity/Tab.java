package com.gm.data.util.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 案例表格表(Tab)表实体类
 *
 * @author Jas°
 * @since 2021-03-29 20:43:56
 */
@Data
public class Tab implements Serializable{
    private Long id;
    private String column1;
    private String str;
    private Date date;
    private Long userId;
    private Boolean del;
}
