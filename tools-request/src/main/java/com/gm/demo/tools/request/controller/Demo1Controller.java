package com.gm.demo.tools.request.controller;

import cn.gmlee.tools.base.mod.JsonResult;
import com.gm.demo.tools.request.controller.vo.Demo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Jas°
 * @date 2021/4/25 (周日)
 */
@RestController
public class Demo1Controller {

    @RequestMapping("test1")
    public JsonResult<Demo> test1(Demo demo) throws Exception {
        return JsonResult.OK.newly(demo);
    }

    @RequestMapping("test2")
    public JsonResult<Demo> test2(@RequestBody Demo demo) throws Exception {
        return JsonResult.OK.newly(demo);
    }

    @RequestMapping("test3")
    public JsonResult<Demo> test3(Demo demo, MultipartFile file) throws Exception {
        if (file != null && !file.isEmpty()) {
            System.out.println(file.getOriginalFilename());
        }
        return JsonResult.OK.newly(demo);
    }
}
