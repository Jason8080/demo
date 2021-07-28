package cn.gmlee.overstep.controller;

import cn.gmlee.overstep.controller.vo.Demo;
import cn.gmlee.tools.base.mod.JsonResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 越权测试.
 *
 * @author Jas°
 * @date 2021/7/28 (周三)
 */
@RestController
public class DemoController {

    @RequestMapping("test1")
    public JsonResult<Demo> test1(Demo demo) throws Exception {
        return JsonResult.OK.newly(demo);
    }

    @RequestMapping("test2")
    public JsonResult<Demo> test2(@RequestBody Demo demo) throws Exception {
        return JsonResult.OK.newly(demo);
    }
}
