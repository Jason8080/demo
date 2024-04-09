package cn.gmlee.demo.k8s.app.demo.controller.vo;

import cn.gmlee.tools.base.anno.Check;
import cn.gmlee.tools.base.anno.El;
import lombok.Data;

import java.io.Serializable;

@Data
@Check
public class Noe implements Serializable {
    @El(conditions = "${a}==${b}", value = "${key}!='' && ${c}!=0")
    private String b;
    private String a;
    private Integer c;
    private String key;
    private String value;
}
