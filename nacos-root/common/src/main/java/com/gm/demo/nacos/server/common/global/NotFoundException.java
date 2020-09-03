package com.gm.demo.nacos.server.common.global;

import com.gm.demo.nacos.server.common.mod.JsonResult;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 捕捉404异常
 *
 * @author Timi
 */
@RestController
public class NotFoundException implements ErrorController {

    @Override
    public String getErrorPath() {
        return "error";
    }

    /**
     * 处理404异常.
     *
     * @return the string
     */
    @RequestMapping("error")
    public Object error(){
        return new JsonResult(404,"哇! 页面走丢了呢");
    }
}