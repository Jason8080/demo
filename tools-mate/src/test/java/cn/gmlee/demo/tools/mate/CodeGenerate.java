package cn.gmlee.demo.tools.mate;

import cn.gmlee.tools.code.CodeGenerator;

/**
 * 代码生成器.
 */
public class CodeGenerate extends CodeGenerator {

    /**
     * 入口.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        init(
                "", "tools-mate", "",
                "cn.gmlee.demo", "",
                "jdbc:mysql://127.0.0.1:3306/tools?serverTimezone=Asia/Shanghai",
                "root", "root"
        );
        execute();
    }
}
