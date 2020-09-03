package com.gm.demo.nacos.server.common.global;

import com.gm.demo.nacos.server.common.ex.SkillException;
import com.gm.demo.nacos.server.common.mod.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理
 *
 * @author Timi.lee
 * @date 2020 /8/28 (周五)
 */
@Component
@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerAdvice.class);

    /**
     * 未知异常
     *
     * @param request   the request
     * @param throwable the throwable
     * @return json result
     */
    @ExceptionHandler({Exception.class, Throwable.class})
    public JsonResult throwable(HttpServletRequest request, Throwable throwable) {
        logger.error("已捕捉: 未知异常", throwable);
        return JsonResult.FAIL.newly(throwable.getMessage());
    }


    /**
     * 技术异常.
     *
     * @param request   the request
     * @param ex the ex
     * @return the json result
     */
    @ExceptionHandler({SkillException.class})
    public JsonResult skillException(HttpServletRequest request, SkillException ex) {
        logger.error("已捕捉: 技术异常", ex);
        return JsonResult.FAIL.newly(ex.getCode(), ex.getMessage());
    }

    /**
     * 数据效验异常
     *
     * @param request the request
     * @param ex      the ex
     * @return json result
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public JsonResult validateException(HttpServletRequest request, ConstraintViolationException ex) {
        logger.debug("已捕捉: 数据效验异常", ex);
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation cv : ex.getConstraintViolations()) {
            sb.append(cv.getMessage());
            sb.append(";");
        }
        return JsonResult.FAIL.newly(sb.substring(0, sb.length() - 1));
    }


    /**
     * 数据绑定异常
     *
     * @param request the request
     * @param ex      the ex
     * @return json result
     */
    @ExceptionHandler({
            ServletRequestBindingException.class, // SpringMVC注解绑定异常
            IllegalStateException.class // 非法参数绑定异常
    })
    public JsonResult validateException(HttpServletRequest request, Exception ex) {
        logger.debug("已捕捉: 数据绑定异常", ex);
        return JsonResult.FAIL.newly("请求数据绑定异常");
    }

    /**
     * 参数类型异常
     *
     * @param request the request
     * @param ex      the ex
     * @return json result
     */
    @ExceptionHandler(TypeMismatchException.class)
    public JsonResult validateException(HttpServletRequest request, TypeMismatchException ex) {
        logger.debug("已捕捉: 参数类型异常", ex);
        return JsonResult.FAIL.newly("不支持的参数类型");
    }

    /**
     * 不支持的请求异常
     *
     * @param request the request
     * @param ex      the ex
     * @return json result
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class,NoHandlerFoundException.class})
    public JsonResult validateException(HttpServletRequest request, ServletException ex) {
        logger.debug("已捕捉: 不支持的请求异常", ex);
        return JsonResult.FAIL.newly("不支持的请求方式");
    }
}
