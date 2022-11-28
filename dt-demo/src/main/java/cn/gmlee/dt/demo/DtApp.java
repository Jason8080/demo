package cn.gmlee.dt.demo;

import cn.gmlee.cc.framework.launcher.EnableServer;
import cn.gmlee.dt.spring.boot.starter.launcher.DtSpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The type Main app.
 */
@EnableServer
@SpringBootApplication
@ComponentScan({"cn.gmlee.dt", "cn.hll.tools"})
public class DtApp {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        DtSpringApplication.run(DtApp.class, args);
    }
}
