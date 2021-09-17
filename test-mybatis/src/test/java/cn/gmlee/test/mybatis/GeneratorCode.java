package cn.gmlee.test.mybatis;


import cn.hll.tools.code.CodeGenerator;

/**
 * .
 *
 * @author Jas°
 * @date 2021/8/16 (周一)
 */
public class GeneratorCode extends CodeGenerator {



    public static void main(String[] args) {
        init("", "test-mybatis", "",
                "cn.gmlee",
                "c_",
                "jdbc:mysql://localhost:3306/db_hllcrm?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai",
                "root",
                "root"
        );

        execute();
    }
}
