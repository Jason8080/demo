package com.gm.demo.nacos.server.common.mod;

import com.gm.demo.nacos.server.common.mod.enums.LogTypeEnum;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LogResult {
    private String url;
    private String method;
    private String ip;
    private int count;
    private Date startTime;
    private Date endTime;
    private String content;
    private List<LogResultDetail> resultDetails = new LinkedList();

    public void beforePutDetail(LogResultDetail loggerResultDetail) {
        if (!loggerResultDetail.isCustom()) {
            ++this.count;
        }

        loggerResultDetail.setCount(this.count);
        this.resultDetails.add(loggerResultDetail);
    }

    public void throwingPutDetail(LogResultDetail loggerResultDetail) {
        try {
            Iterator var2 = this.resultDetails.iterator();

            while(var2.hasNext()) {
                LogResultDetail detail = (LogResultDetail)var2.next();
                if (!StringUtils.isEmpty(detail.getClassMethod()) && detail.getClassMethod().equals(loggerResultDetail.getClassMethod())) {
                    detail.setCode(loggerResultDetail.getCode());
                    detail.setMsg(loggerResultDetail.getMsg());
                    detail.setErrorMsg(loggerResultDetail.getErrorMsg());
                    detail.setLogType(loggerResultDetail.getLogType());
                    detail.setUseTime(loggerResultDetail.getEndTime().getTime() - detail.getStartTime().getTime());
                    break;
                }
            }
        } catch (Exception var4) {
        }

    }

    public void afterPutDetail(LogResultDetail loggerResultDetail) {
        try {
            Iterator var2 = this.resultDetails.iterator();

            while(var2.hasNext()) {
                LogResultDetail detail = (LogResultDetail)var2.next();
                if (!StringUtils.isEmpty(detail.getClassMethod()) && detail.getClassMethod().equals(loggerResultDetail.getClassMethod())) {
                    detail.setCode(loggerResultDetail.getCode());
                    detail.setMsg(loggerResultDetail.getMsg());
                    detail.setEndTime(loggerResultDetail.getEndTime());
                    detail.setUseTime(detail.getEndTime().getTime() - detail.getStartTime().getTime());
                    if (detail.getCode() > 0) {
                        detail.setLogType(LogTypeEnum.WARN.getName());
                    }
                    break;
                }
            }
        } catch (Exception var4) {
        }

    }

    public LogResult() {
    }

    public LogResult(String url, String ip) {
        this.url = url;
        this.ip = ip;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<LogResultDetail> getResultDetails() {
        return this.resultDetails;
    }

    public void setResultDetails(List<LogResultDetail> resultDetails) {
        this.resultDetails = resultDetails;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
