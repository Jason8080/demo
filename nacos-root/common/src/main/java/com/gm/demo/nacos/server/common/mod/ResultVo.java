package com.gm.demo.nacos.server.common.mod;


import com.gm.demo.nacos.server.common.mod.enums.BaseErrorEnum;

public class ResultVo {
    private int code;
    private String msg;
    private Object data;

    public String toString() {
        return "ResultVo{code=" + this.code + ", msg='" + this.msg + '\'' + ", data=" + this.data + '}';
    }

    public ResultVo() {
    }

    public ResultVo(BaseErrorEnum baseErrorEnum) {
        this.code = baseErrorEnum.getCode();
        this.msg = baseErrorEnum.getMsg();
    }

    public ResultVo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVo(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
