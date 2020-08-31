package com.gm.demo.nacos.server.common.mod;

import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.github.pagehelper.PageInfo;
import com.gm.demo.nacos.server.common.mod.enums.BaseErrorEnum;
import com.gm.demo.nacos.server.common.mod.enums.BaseResultEnum;
import com.gm.demo.nacos.server.common.mod.enums.LogTypeEnum;
import com.gm.demo.nacos.server.common.util.IdUtil;
import com.gm.demo.nacos.server.common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;

public class Result<T> {

    private Logger logger = LoggerFactory.getLogger(Result.class);

    private String uuid;
    private int code;
    private String msg;
    private String desc;
    private boolean rollback;
    private boolean rollbackRun;
    private String fstPlatform;
    private String fstApplication;
    private String nowPlatform;
    private String nowApplication;
    private T data;
    private Date startTime;
    private Date endTime;
    private Long useTime;
    private String errorMsg;
    private String allErrorMsg;
    private StringBuilder content;
    private LogResult logResult;
    private boolean failReturnData;

    public Result() {
        this.desc = "";
        this.rollback = false;
        this.rollbackRun = false;
        this.content = new StringBuilder();
        this.failReturnData = false;
    }

    public Result(RemoteResultVo remoteResultVo) {
        this.desc = "";
        this.rollback = false;
        this.rollbackRun = false;
        this.content = new StringBuilder();
        this.failReturnData = false;
        this.uuid = remoteResultVo.getUuid();
        this.code = remoteResultVo.getCode();
        this.msg = remoteResultVo.getMsg();
        this.desc = remoteResultVo.getDesc();
        this.rollback = remoteResultVo.isRollback();
        this.rollbackRun = remoteResultVo.isRollbackRun();
        this.fstApplication = remoteResultVo.getFstApplication();
        this.fstPlatform = remoteResultVo.getFstPlatform();
        this.data = (T) remoteResultVo.getData();
        this.startTime = remoteResultVo.getStartTime();
        this.endTime = remoteResultVo.getEndTime();
        this.useTime = remoteResultVo.getUseTime();
        this.errorMsg = remoteResultVo.getErrorMsg();
        this.allErrorMsg = remoteResultVo.getAllErrorMsg();
        this.content = remoteResultVo.getContent();
        this.logResult = remoteResultVo.getLogResult();
        this.failReturnData = remoteResultVo.isFailReturnData();
    }

    public boolean isFailReturnData() {
        return this.failReturnData;
    }

    public void editFailReturnData(boolean failReturnData) {
        this.failReturnData = failReturnData;
    }

    public Result(String platform, int port) {
        this((HttpServletRequest)null, platform, "", "", "", port, "");
    }

    public Result(HttpServletRequest request, String platform, String className, String classMethodName, String desc, int port, String applicationName) {
        this.desc = "";
        this.rollback = false;
        this.rollbackRun = false;
        this.content = new StringBuilder();
        this.failReturnData = false;
        this.uuid = IdUtil.uuidReplace();
        this.startTime = new Date();
        this.desc = desc;
        this.code = BaseResultEnum.SUCCESS.getCode();
        this.msg = BaseResultEnum.SUCCESS.getMsg();
        this.fstPlatform = platform;
        this.fstApplication = applicationName;
        this.nowPlatform = this.fstPlatform;
        this.nowApplication = applicationName;
        if (request == null) {
            this.logResult = new LogResult("", "");
        } else {
            this.logResult = new LogResult(this.getRequstURL(request), getClientIp(request));
        }

        this.logResult.setCount(1);
        this.logResult.setStartTime(new Date());
        this.content.append("\r\n").append(this.uuid).append("_request ").append("url=").append(this.logResult.getUrl()).append("，");
        this.content.append("application=").append(platform).append("/").append(applicationName).append(", desc=").append(desc).append("，method=").append(className).append(".").append(classMethodName);
        LogResultDetail detail = new LogResultDetail();
        detail.setLogType(LogTypeEnum.INFO.getName());
        detail.setApiMethod(true);
        detail.setPlatform(platform);
        detail.setStartTime(new Date());
        detail.setCount(this.logResult.getCount());
        detail.setUrl(this.logResult.getUrl());
        detail.setDesc(desc);
        detail.setClassMethod(className + "." + classMethodName);
        detail.setApplicationName(applicationName);
        this.logResult.getResultDetails().add(detail);
    }

    public void log(String classMethod) {
        Iterator var2;
        LogResultDetail detail;
        if (((LogResultDetail)this.getLogResult().getResultDetails().get(0)).getClassMethod().equals(classMethod)) {
            this.endTime = new Date();
            this.useTime = this.endTime.getTime() - this.startTime.getTime();
            var2 = this.getLogResult().getResultDetails().iterator();

            while(var2.hasNext()) {
                detail = (LogResultDetail)var2.next();
                if (!StringUtils.isEmpty(detail.getClassMethod()) && detail.getClassMethod().equals(classMethod)) {
                    detail.setCode(this.code);
                    detail.setMsg(this.msg);
                    detail.setEndTime(this.endTime);
                    detail.setUseTime(this.useTime);
                    break;
                }
            }

            this.outputLog();
        } else {
            var2 = this.getLogResult().getResultDetails().iterator();

            while(var2.hasNext()) {
                detail = (LogResultDetail)var2.next();
                if (!StringUtils.isEmpty(detail.getClassMethod()) && detail.getClassMethod().equals(classMethod)) {
                    detail.setCode(this.code);
                    detail.setMsg(this.msg);
                    detail.setEndTime(new Date());
                    detail.setUseTime(detail.getEndTime().getTime() - detail.getStartTime().getTime());
                    break;
                }
            }
        }

    }

    public boolean isSuccess() {
        return this.code == BaseResultEnum.SUCCESS.getCode();
    }

    public boolean isFail() {
        return !this.isSuccess();
    }

    private void outputLog() {
        Iterator var1 = this.logResult.getResultDetails().iterator();

        while(var1.hasNext()) {
            LogResultDetail detail = (LogResultDetail)var1.next();
            this.content.append("\r\n");
            this.content.append(this.uuid);
            this.content.append("_");
            this.content.append(detail.returnContent());
        }

        this.content.append("\r\n");
        this.content.append(this.uuid);
        this.content.append("_redata: ");
        if (this.isSuccess() && this.getData() instanceof PageInfo) {
            this.content.append(JsonUtil.toJson(this.PageRTN()));
        } else {
            this.content.append(JsonUtil.toJson(this.RTN()));
        }

        if (!StringUtils.isEmpty(this.errorMsg)) {
            this.errorMsg = "\r\n" + this.uuid + "_error：" + this.errorMsg + "。";
            this.content.append(this.getErrorMsg());
        }

        this.logger.info(this.content.toString());
    }

    public void infoLog(String msgInfo) {
        if (!StringUtils.isEmpty(msgInfo)) {
            LogResultDetail LogResultDetail = new LogResultDetail(this.nowPlatform, msgInfo, true);
            this.beforePutDetail(LogResultDetail);
        }

    }

    public void infoLog(String msgInfo, Object... objects) {
        if (!StringUtils.isEmpty(msgInfo)) {
            LogResultDetail LogResultDetail = new LogResultDetail(this.nowPlatform, msgInfo, true, objects);
            this.beforePutDetail(LogResultDetail);
        }

    }

    public void error(int code, String msg) {
        this.error(code, msg, null);
    }

    public void error(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public void error(BaseErrorEnum baseErrorEnum) {
        this.error(baseErrorEnum, null);
    }

    public void error(BaseErrorEnum baseErrorEnum, T data) {
        this.code = baseErrorEnum.getCode();
        this.msg = baseErrorEnum.getMsg();
        this.data = data;
    }

    public Result errorRTN(int code, String msg) {
        this.error(code, msg);
        return this;
    }

    public Result errorRTN(int code, String msg, T data) {
        this.error(code, msg, data);
        return this;
    }

    public Result errorRTN(BaseErrorEnum baseErrorEnum) {
        this.error(baseErrorEnum);
        return this;
    }

    public Result success() {
        this.code = BaseResultEnum.SUCCESS.getCode();
        this.msg = BaseResultEnum.SUCCESS.getMsg();
        this.data = null;
        return this;
    }

    public Result success(T data) {
        this.code = BaseResultEnum.SUCCESS.getCode();
        this.msg = BaseResultEnum.SUCCESS.getMsg();
        this.editData(data);
        return this;
    }

    public Result errorRTN(BaseErrorEnum baseErrorEnum, T data) {
        this.error(baseErrorEnum, data);
        return this;
    }

    public String toString() {
        return "Result{uuid='" + this.uuid + '\'' + ", code=" + this.code + ", msg='" + this.msg + '\'' + ", fstPlatform='" + this.fstPlatform + '\'' + ", nowPlatform='" + this.nowPlatform + '\'' + ", data=" + this.data + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", useTime=" + this.useTime + ", errorMsg='" + this.errorMsg + '\'' + ", allErrorMsg='" + this.allErrorMsg + '\'' + ", content=" + this.content + ", logResult=" + this.logResult + '}';
    }

    public ResultVo RTN() {
        return new ResultVo(this.code, this.msg, this.data);
    }

    public PageResultVo PageRTN() {
        return new PageResultVo(this.code, this.msg, this.data);
    }

    public String getExceptionCause(Throwable cause) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(cause.toString());
        StackTraceElement[] stes = cause.getStackTrace();
        if (ArrayUtils.isNotEmpty(stes)) {
            StringBuilder allMsg = new StringBuilder();
            StackTraceElement stack = stes[0];
            stringBuilder.append(this.stackTraceElementGetStr(stack));
            allMsg.append(this.stackTraceElementGetStr(stack));

            for(int i = 1; i < stes.length; ++i) {
                allMsg.append(this.stackTraceElementGetStr(stes[i]));
                if (stes[i].toString().contains("qiyu")) {
                    stringBuilder.append(this.stackTraceElementGetStr(stes[i]));
                }
            }

            this.allErrorMsg = allMsg.toString();
        }

        return stringBuilder.toString();
    }

    private String stackTraceElementGetStr(StackTraceElement stackTraceElement) {
        return "\r\n" + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + "(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")";
    }

    public String getExceptionStackTrace(Throwable cause) {
        String stackTrace = this.getExceptionCause(cause);
        if (cause.getCause() != null) {
            stackTrace = this.getExceptionStackTrace(cause.getCause());
        }

        return stackTrace;
    }

    public void exception(String errorMessage) {
        this.code = this.code == BaseResultEnum.SUCCESS.getCode() ? BaseResultEnum.METHOD_EXCEPTION.getCode() : this.code;
        this.errorMsg = errorMessage;
        this.logger.error(this.content + "\r\n" + this.uuid + "_相关错误信息=" + this.errorMsg + "\r\n" + this.uuid + "_完整错误信息=" + this.allErrorMsg);
        this.msg = this.msg.equals(BaseResultEnum.SUCCESS.getMsg()) ? BaseResultEnum.METHOD_EXCEPTION.getMsg() : this.msg;
        this.data = null;
    }

    public String getRequstURL(HttpServletRequest request) {
        if (request == null) {
            return "";
        } else {
            String path = request.getRequestURI();
            return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
        }
    }

    public static String getClientIp(HttpServletRequest request) {
        if (request == null) {
            return "";
        } else {
            String ip = "";

            try {
                ip = request.getHeader("x-forwarded-for");
                System.setProperty("java.net.preferIPv4Stack", "true");
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("Proxy-Client-IP");
                }

                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("WL-Proxy-Client-IP");
                }

                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getRemoteAddr();
                    if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
                        InetAddress inet = null;

                        try {
                            inet = InetAddress.getLocalHost();
                        } catch (UnknownHostException var4) {
                            var4.printStackTrace();
                            System.out.println("getClientIp 异常1");
                        }

                        if (!ObjectUtils.isEmpty(inet)) {
                            ip = inet.getHostAddress();
                        }
                    }
                }

                if (ip != null && ip.length() > 15 && ",".indexOf(ip) > 0) {
                    ip = ip.substring(0, ip.indexOf(","));
                }
            } catch (Exception var5) {
                var5.printStackTrace();
                System.out.println("getClientIp 异常2");
            }

            return ip;
        }
    }

    public void beforePutDetail(LogResultDetail LogResultDetail) {
        this.logResult.beforePutDetail(LogResultDetail);
    }

    public void throwingPutDetail(LogResultDetail LogResultDetail) {
        this.logResult.throwingPutDetail(LogResultDetail);
    }

    public void LogResultDetail(LogResultDetail LogResultDetail) {
        this.logResult.afterPutDetail(LogResultDetail);
    }

    public static String getServerIp() {
        String localip = null;
        String netip = null;

        try {
            Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            boolean finded = false;

            while(true) {
                while(netInterfaces.hasMoreElements() && !finded) {
                    NetworkInterface ni = (NetworkInterface)netInterfaces.nextElement();
                    Enumeration address = ni.getInetAddresses();

                    while(address.hasMoreElements()) {
                        ip = (InetAddress)address.nextElement();
                        if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                            netip = ip.getHostAddress();
                            finded = true;
                            break;
                        }

                        if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                            localip = ip.getHostAddress();
                        }
                    }
                }

                return netip != null && !"".equals(netip) ? netip : localip;
            }
        } catch (SocketException var7) {
            var7.printStackTrace();
            return netip != null && !"".equals(netip) ? netip : localip;
        }
    }

    public LogResult getLogResult() {
        return this.logResult;
    }

    public int getCode() {
        return this.code;
    }

    public void editCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void editMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return this.data;
    }

    public void editData(T data) {
        this.data = data;
    }

    public String getFstPlatform() {
        return this.fstPlatform;
    }

    public String getNowPlatform() {
        return this.nowPlatform;
    }

    public void editNowPlatform(String nowPlatform) {
        this.nowPlatform = nowPlatform;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void editErrorMsg(String errorMsg) {
        this.errorMsg = StringUtils.isEmpty(this.errorMsg) ? errorMsg : this.errorMsg + "," + errorMsg;
    }

    public StringBuilder getContent() {
        return this.content;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void editStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void editEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getUseTime() {
        return this.useTime;
    }

    public void editUseTime(Long useTime) {
        this.useTime = useTime;
    }

    public String getAllErrorMsg() {
        return this.allErrorMsg;
    }

    public String getUuid() {
        return this.uuid;
    }

    public boolean isRollback() {
        return this.rollback;
    }

    public void editRollback(boolean rollback) {
        this.rollback = rollback;
    }

    public boolean isRollbackRun() {
        return this.rollbackRun;
    }

    public void editRollbackRun(boolean rollbackRun) {
        this.rollbackRun = rollbackRun;
    }

    public String getDesc() {
        return this.desc;
    }

    public void editDesc(String desc) {
        this.desc = desc;
    }

    public String getFstApplication() {
        return this.fstApplication;
    }

    public void editApplication(String fstApplication) {
        this.fstApplication = fstApplication;
    }

    public String getNowApplication() {
        return this.nowApplication;
    }

    public void editNowApplication(String nowApplication) {
        this.nowApplication = nowApplication;
    }
}
