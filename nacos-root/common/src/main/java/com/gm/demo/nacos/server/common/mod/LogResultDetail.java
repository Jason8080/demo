package com.gm.demo.nacos.server.common.mod;


import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gm.demo.nacos.server.common.mod.enums.BaseResultEnum;
import com.gm.demo.nacos.server.common.util.JsonUtil;
import org.junit.platform.commons.util.StringUtils;

import java.util.Date;

public class LogResultDetail {
    private String url;
    private String method;
    private String api;
    private String classMethod;
    private String desc;
    private String params;
    private int count;
    private Date startTime;
    private Date endTime;
    private long useTime;
    private Object[] strings;
    private String logMsg;
    private String logType;
    private int code;
    private String msg;
    private String errorMsg;
    private String platform;
    private String applicationName;
    private boolean isCustom;
    private boolean apiMethod;

    public LogResultDetail() {
        this.isCustom = false;
        this.apiMethod = false;
    }

    public LogResultDetail(String platform, String logMsg, boolean isCustom) {
        this.isCustom = false;
        this.apiMethod = false;
        this.platform = platform;
        this.logMsg = logMsg;
        this.isCustom = isCustom;
        if (this.code != BaseResultEnum.SUCCESS.getCode()) {
            this.logType = "warn";
        }

    }

    public LogResultDetail(String platform, String logMsg, boolean isCustom, Object... strings) {
        this(platform, logMsg, isCustom);
        this.setStrings(strings);
    }

    public LogResultDetail(String platform, Date startTime, int count, String msg, String logType, String method, String api, String classMethod, String desc, String params, String applicationName) {
        this.isCustom = false;
        this.apiMethod = false;
        this.platform = platform;
        this.msg = msg;
        this.logType = logType;
        this.count = count;
        this.method = method;
        this.api = api;
        this.classMethod = classMethod;
        this.desc = desc;
        this.params = params;
        this.startTime = startTime;
        this.applicationName = applicationName;
    }

    public LogResultDetail(String platform, Date endTime, String classMethod, String msg, int code, String logType, String errorMsg) {
        this.isCustom = false;
        this.apiMethod = false;
        this.platform = platform;
        this.endTime = endTime;
        this.classMethod = classMethod;
        this.msg = msg;
        this.code = code;
        this.logType = logType;
        this.errorMsg = errorMsg;
    }

    public LogResultDetail(String platform, Date endTime, String classMethod, String msg, int code) {
        this.isCustom = false;
        this.apiMethod = false;
        this.platform = platform;
        this.endTime = endTime;
        this.classMethod = classMethod;
        this.msg = msg;
        this.code = code;
        if (this.code != BaseResultEnum.SUCCESS.getCode()) {
            this.logType = "warn";
        }

    }

    public String returnContent() {
        String content;
        if (this.isCustom) {
            if (ObjectUtils.isNotNull(this.strings) && this.strings.length > 0) {
                StringBuilder stringBuilder = new StringBuilder(this.logMsg);
                int index = -1;
                Object[] var4 = this.strings;
                int var5 = var4.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    Object string = var4[var6];
                    if (index > -1) {
                        index += 2;
                    }

                    index = stringBuilder.indexOf("{}", index);
                    if (index < 0) {
                        break;
                    }

                    stringBuilder.replace(index, index + 2, JsonUtil.toJson(string));
                }

                this.logMsg = stringBuilder.toString();
            }

            content = this.count + "_log:  " + this.logMsg;
        } else {
            content = "step" + this.count + ":  aplication=" + this.platform + "/" + this.applicationName + ", desc=" + this.desc + ", method=" + this.classMethod + ", code=" + this.code + ", msg=" + this.msg + ", level=" + this.logType + ", time=" + this.useTime + "ms" + (this.apiMethod ? "" : ", param=>" + this.params);
        }

        if (StringUtils.isNotBlank(this.errorMsg)) {
            content = content + "\r\nerror about:" + this.errorMsg;
        }

        return content;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getApi() {
        return this.api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getParams() {
        return this.params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getClassMethod() {
        return this.classMethod;
    }

    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isCustom() {
        return this.isCustom;
    }

    public void setCustom(boolean custom) {
        this.isCustom = custom;
    }

    public String getLogMsg() {
        return this.logMsg;
    }

    public void setLogMsg(String logMsg) {
        this.logMsg = logMsg;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getLogType() {
        return this.logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getUseTime() {
        return this.useTime;
    }

    public void setUseTime(long useTime) {
        this.useTime = useTime;
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object[] getStrings() {
        return this.strings;
    }

    public void setStrings(Object[] strings) {
        this.strings = strings;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isApiMethod() {
        return this.apiMethod;
    }

    public void setApiMethod(boolean apiMethod) {
        this.apiMethod = apiMethod;
    }

    public String getApplicationName() {
        return this.applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
}
