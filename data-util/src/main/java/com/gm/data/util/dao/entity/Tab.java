package com.gm.data.util.dao.entity;

import java.util.Date;

import cn.gmlee.tools.datalog.annotation.Ignore;
import cn.gmlee.tools.datalog.annotation.Note;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * 案例表格表(Tab)表实体类
 *
 * @author Jas°
 * @since 2021-03-29 20:43:56
 */
@TableName("t_pos_tab")
public class Tab extends Model<Tab> {
    @Note("主键")
    private Long id;
    @Note("列名")
    private String column1;
    @Note("字符")
    private String str;
    private Date date;
    private Long userId;
    private Boolean del;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Tab{" +
                "id=" + id +
                ", column1='" + column1 + '\'' +
                ", str='" + str + '\'' +
                ", date=" + date +
                ", userId=" + userId +
                ", del=" + del +
                '}';
    }
}
