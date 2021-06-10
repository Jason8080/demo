package com.gm.demo.tools.sharding.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jas°
 * @date 2021/6/10 (周四)
 */
@Data
@TableName("t_tab")
public class Tab implements Serializable {
    private Long id;
    private String column1;
    private String str;
    private Date date;
    private Long userId;
}
