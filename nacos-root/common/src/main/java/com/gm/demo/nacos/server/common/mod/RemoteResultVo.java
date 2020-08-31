package com.gm.demo.nacos.server.common.mod;

import java.util.Date;

public class RemoteResultVo {
    private String uuid;
    private int code;
    private String msg;
    private String desc = "";
    private boolean rollback = false;
    private boolean rollbackRun = false;
    private String fstPlatform;
    private String fstApplication;
    private String nowPlatform;
    private String nowApplication;
    private Object data;
    private Date startTime;
    private Date endTime;
    private Long useTime;
    private String errorMsg;
    private String allErrorMsg;
    private StringBuilder content = new StringBuilder();
    private LogResult logResult;
    private boolean failReturnData = false;

    public RemoteResultVo() {
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isRollback() {
        return this.rollback;
    }

    public void setRollback(boolean rollback) {
        this.rollback = rollback;
    }

    public boolean isRollbackRun() {
        return this.rollbackRun;
    }

    public void setRollbackRun(boolean rollbackRun) {
        this.rollbackRun = rollbackRun;
    }

    public String getFstPlatform() {
        return this.fstPlatform;
    }

    public void setFstPlatform(String fstPlatform) {
        this.fstPlatform = fstPlatform;
    }

    public String getNowPlatform() {
        return this.nowPlatform;
    }

    public void setNowPlatform(String nowPlatform) {
        this.nowPlatform = nowPlatform;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
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

    public Long getUseTime() {
        return this.useTime;
    }

    public void setUseTime(Long useTime) {
        this.useTime = useTime;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getAllErrorMsg() {
        return this.allErrorMsg;
    }

    public void setAllErrorMsg(String allErrorMsg) {
        this.allErrorMsg = allErrorMsg;
    }

    public StringBuilder getContent() {
        return this.content;
    }

    public void setContent(StringBuilder content) {
        this.content = content;
    }

    public LogResult getLogResult() {
        return this.logResult;
    }

    public void setLogResult(LogResult logResult) {
        this.logResult = logResult;
    }

    public boolean isFailReturnData() {
        return this.failReturnData;
    }

    public void setFailReturnData(boolean failReturnData) {
        this.failReturnData = failReturnData;
    }

    public String getFstApplication() {
        return this.fstApplication;
    }

    public void setFstApplication(String fstApplication) {
        this.fstApplication = fstApplication;
    }

    public String getNowApplication() {
        return this.nowApplication;
    }

    public void setNowApplication(String nowApplication) {
        this.nowApplication = nowApplication;
    }
}
