package cn.gmlee.demo.k8s.app.demo.controller;

import cn.gmlee.tools.base.anno.ApiPrint;
import cn.gmlee.tools.base.mod.Kv;
import cn.gmlee.tools.base.mod.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * API测试.
 */
@RestController
@Api(tags = {"API测试"})
@RequestMapping("api")
public class TestApi {

    /**
     * Get json result.
     *
     * @param kv the kv
     * @return the json result
     */
    @ApiPrint("GET")
    @ApiOperation(value = "get")
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public R get(Kv<String, String> kv) {
        return R.OK.newly(kv);
    }

    /**
     * Post json result.
     *
     * @param kv the kv
     * @return the json result
     */
    @ApiPrint("POST")
    @ApiOperation(value = "post")
    @RequestMapping(value = "post", method = RequestMethod.POST)
    public R post(@RequestBody Kv<String, String> kv) {
        return R.OK.newly(kv);
    }

}
