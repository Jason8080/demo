package cn.gmlee.demo.dynamic.api.config;

import cn.gmlee.demo.dynamic.api.adapter.ArgsAdapterController;
import cn.gmlee.tools.spring.util.MvcUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
public class DynamicApiAutoConfiguration {

    private final ArgsAdapterController controller;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        MvcUtil.register("/dynamic/api", "Post", controller, "handle");
    }

}
