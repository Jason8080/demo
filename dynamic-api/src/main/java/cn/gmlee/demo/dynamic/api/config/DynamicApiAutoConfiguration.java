package cn.gmlee.demo.dynamic.api.config;

import cn.gmlee.demo.dynamic.api.adapter.ArgsAdapterController;
import cn.gmlee.tools.spring.util.MvcUtil;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

public class DynamicApiAutoConfiguration {

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        MvcUtil.register("/dynamic/api", "Post", ArgsAdapterController.class);
    }

}
