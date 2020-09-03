package com.gm.demo.nacos.server.common.mod;

import lombok.Data;

import java.io.Serializable;

/**
 * Json响应对象
 * <p>
 * Http状态码
 *
 * @param <T> 响应对象泛型
 * @author Jason
 */
@Data
public class JsonResult<T> implements Serializable {
    /**
     * 操作失败.
     */
    public static final JsonResult FAIL = new JsonResult(500, "操作失败");
    /**
     * 操作成功.
     */
    public static final JsonResult OK = new JsonResult(200, "操作成功");
    /**
     * 认证失败.
     */
    public static final JsonResult AUTH = new JsonResult(502, "认证失败");


    /**
     * 原则上, 所有字段不可更改
     */
    private final Integer code;
    private final String msg;
    private final Long time = System.currentTimeMillis();
    private final T data;

    public JsonResult() {
        this.code = OK.code;
        this.msg = OK.msg;
        this.data = null;
    }

    public JsonResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public JsonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public static JsonResult ok(){
        return OK;
    }

    public static JsonResult fail(){
        return FAIL;
    }

    public JsonResult newly(String msg){
        return new JsonResult(this.code, msg, this.data);
    }

    public JsonResult newly(T data){
        return new JsonResult(this.code, this.msg, data);
    }

    public JsonResult newly(Integer code, String msg){
        return new JsonResult(code, msg, data);
    }
}
