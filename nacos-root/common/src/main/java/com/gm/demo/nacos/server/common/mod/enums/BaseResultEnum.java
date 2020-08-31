//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.gm.demo.nacos.server.common.mod.enums;

public enum BaseResultEnum implements BaseErrorEnum {
    SUCCESS(0, "操作成功"),
    METHOD_EXCEPTION(1, "操作失败"),
    TOKEN_EXCEPTION(2, "TOKEN无效或者过期"),
    AOP_EXCEPTION(3, "AOP处理异常"),
    NO_LOGIN(4, "用户未登录！"),
    HAVED_LOGIN(5, "您的账号已在其他设备登录，请重新登录！"),
    IS_EXPRIED(6, "您的使用期限已到期，请先充值！"),
    CHECK_PARAM_ERROR(7, " is null");

    private int code;
    private String msg;

    private BaseResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
