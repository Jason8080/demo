package com.gm.data.util.controller.vo;

import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * 案例表格表(Tab)表实体类
 *
 * @author Jas°
 * @since 2021-03-29 20:43:57
 */
@SuppressWarnings("serial")
public class TabVo extends Model<TabVo> {
    //主键
    private Long id;
    //列one
    private String column1;
    //字符串
    private String str;
    //时间
    private Date date;
    //用户编号
    private Long userId;
    //删除标识(数据库设置默认值0)
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
}
