package com.gm.demo.nacos.server.common.mod;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Map;

/**
 * Json响应对象
 * <p>
 * Http状态码
 *
 * @param <T> 响应对象泛型
 * @author Jason
 */
public class JsonResult<T> implements Serializable {
    /**
     * 操作失败.
     */
    public static final JsonResult FAIL = new JsonResult(500, "操作失败");
    /**
     * 操作成功.
     */
    public static final JsonResult SUCCESS = new JsonResult(200, "操作成功");


    private final Integer code;
    private final String msg;
    // Map<String, Object> T
    private final T data;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private final Long time = System.currentTimeMillis();


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
        return SUCCESS;
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
}
