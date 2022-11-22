package cn.gmlee.demo.dt;

import cn.hll.tools.code.CodeGenerator;

/**
 * The type Code generate.
 */
public class CodeGenerate extends CodeGenerator {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        init("", "dt-demo", "",
                "cn.gmlee",
                "",
                "jdbc:mysql://rm-wz9r8j008062a230r.mysql.rds.aliyuncs.com:3306/db_driver_manager?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&noAccessToProcedureBodies=true",
                "hllcrm_rw",
                "fRQQuCegchr5KL0_m0jy"
        );

        execute();
    }
}
