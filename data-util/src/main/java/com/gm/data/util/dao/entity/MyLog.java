package com.gm.data.util.dao.entity;

import cn.gmlee.tools.datalog.model.Datalog;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Jas°
 * @date 2021/4/6 (周二)
 */
@Data
@TableName("t_pos_my_log")
public class MyLog extends Datalog {
    private String ok;
}
