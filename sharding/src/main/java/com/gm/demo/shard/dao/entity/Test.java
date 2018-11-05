package com.gm.demo.shard.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Jason
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("test_")
public class Test extends Model<Test> implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}