package cn.gmlee.demo.k8s.app.demo.controller.vo;

import cn.gmlee.tools.base.anno.Check;
import cn.gmlee.tools.base.anno.El;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Check
public class Noe implements Serializable {
    private String b;
    private String a;
    private Integer c;
    private String key;
    @NotEmpty(message = "值内容不可以为空")
    @El(conditions = "${a}==${b}", value = "${key}!='' && ${value}!=null && ${value}!=''", message = "值内容是空")
    private String value;
}
