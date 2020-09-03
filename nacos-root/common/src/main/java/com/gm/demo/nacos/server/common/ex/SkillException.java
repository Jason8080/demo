package com.gm.demo.nacos.server.common.ex;

/**
 * 技术中台的异常
 *
 * @author Timi.lee
 * @date 2020/9/3 (周四)
 */
public class SkillException extends RuntimeException {

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public SkillException(Integer code) {
        this.code = code;
    }

    public SkillException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public SkillException(String message, Throwable cause, Integer code) {
        super(message, cause);
        this.code = code;
    }

    public SkillException(Throwable cause, Integer code) {
        super(cause);
        this.code = code;
    }

    public SkillException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }
}
