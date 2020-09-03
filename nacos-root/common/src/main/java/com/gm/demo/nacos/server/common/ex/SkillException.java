package com.gm.demo.nacos.server.common.ex;

/**
 * 技术中台的异常
 *
 * @author Timi.lee
 * @date 2020/9/3 (周四)
 */
public class SkillException extends RuntimeException {
    public SkillException() {
        super("请先登录");
    }

    public SkillException(String message) {
        super(message);
    }

    public SkillException(String message, Throwable cause) {
        super(message, cause);
    }

    public SkillException(Throwable cause) {
        super(cause);
    }

    public SkillException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
