package com.gm.demo.nacos.server.common.global;

import com.gm.demo.nacos.server.common.mod.JsonResult;
import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理
 *
 * @author Timi.lee
 * @date 2020/8/28 (周五)
 */
@Component
@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {

    /**
     * 未知异常
     *
     * @param request
     * @param throwable
     * @return
     */
    @ExceptionHandler({Exception.class, Throwable.class})
    public JsonResult throwable(HttpServletRequest request, Throwable throwable) {
        return JsonResult.FAIL.newly(throwable.getMessage());
    }

    /**
     * 数据效验异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public JsonResult validateException(HttpServletRequest request, ConstraintViolationException ex) {
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
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler({
            ServletRequestBindingException.class, // SpringMVC注解绑定异常
            IllegalStateException.class // 非法参数绑定异常
    })
    public JsonResult validateException(HttpServletRequest request, Exception ex) {
        return JsonResult.FAIL.newly("请求参数异常");
    }

    /**
     * 参数类型异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(TypeMismatchException.class)
    public JsonResult validateException(HttpServletRequest request, TypeMismatchException ex) {
        return JsonResult.FAIL.newly("不支持的参数类型");
    }

    /**
     * 不支持方法异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JsonResult validateException(HttpServletRequest request, HttpRequestMethodNotSupportedException ex) {
        return JsonResult.FAIL.newly("不支持的请求方式");
    }
}