package cn.gmlee.demo.k8s.app.demo.controller;

import cn.gmlee.demo.k8s.app.demo.controller.vo.Noe;
import cn.gmlee.tools.base.anno.ApiPrint;
import cn.gmlee.tools.base.mod.Kv;
import cn.gmlee.tools.base.mod.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Check测试.
 */
@RestController
@Api(tags = {"Check测试"})
@RequestMapping("check")
public class TestCheck {

    /**
     * Get json result.
     *
     * @param noe the noe
     * @return the json result
     */
    @ApiPrint("GET")
    @ApiOperation(value = "get")
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public R get(@Valid Noe noe) {
        return R.OK.newly(noe);
    }
}
