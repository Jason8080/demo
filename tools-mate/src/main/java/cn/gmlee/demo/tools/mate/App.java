package cn.gmlee.demo.tools.mate;

import cn.gmlee.tools.mybatis.config.plus.MyBatisPlusConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 启动类.
 */
@MapperScan("**.dao.mapper.**")
@ComponentScan({"cn.gmlee"})
@SpringBootApplication(exclude = MyBatisPlusConfiguration.class)
public class App {
    /**
     * 入口.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
