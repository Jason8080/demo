package cn.gmlee.demo.k8s.app.demo.controller.vo;

import cn.gmlee.tools.base.anno.Check;
import cn.gmlee.tools.base.anno.El;
import cn.gmlee.tools.base.anno.Enums;
import cn.gmlee.tools.base.enums.XCode;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 原则: ①当表达式无法执行时忽略(即放行) ②当表达式结果不是true/false时忽略(即放行)
 */
@Data
@Check(@El("...."))
public class Noe implements Serializable {
    private String b;
    /**
     * 需求: a必传
     */
//    @El("'${a}'!=null")
    private String a;
    /**
     * 需求: a+b 需要 >c
     */
//    @El("${a} + ${b} > ${c}")
    private Integer c;
    /**
     * 需求: key的内容仅允许传入XCode枚举范围的值
     */
//    @Enums(enums = XCode.class)
    private String key;
    /***
     * 需求: value不为空 (原生注解为了演示兼容)
     * 真实需求: a字段等于b字段时, 需要校验key不等于''字符串, 同时value必传且不可为''字符串
     */
//    @NotEmpty(message = "值内容不可以为空") // 原生可用
    @El(conditions = {"'${a}'=='${b}'"}, value = {"${key}!='' && ${value}!=null && ${value}!=''"}, message = "演示消息")
    private String value;
}
