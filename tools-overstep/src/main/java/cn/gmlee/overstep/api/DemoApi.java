package cn.gmlee.overstep.api;

import cn.gmlee.overstep.controller.vo.Demo;
import cn.gmlee.tools.base.mod.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * .
 *
 * @author Jas °
 * @date 2021 /9/8 (周三)
 */
@FeignClient(name = "overstep", url = "http://127.0.0.1:8080/")
public interface DemoApi {
    /**
     * Test 2 json result.
     *
     * @param demo the demo
     * @return the json result
     * @throws Exception the exception
     */
    @RequestMapping("test2")
    JsonResult<Demo> test2(@RequestBody Demo demo) throws Exception ;
}
