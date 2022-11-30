package cn.gmlee.dt.demo.controller;


import cn.gmlee.dt.demo.service.impl.ExTabService;
import cn.hll.tools.base.anno.ApiPrint;
import cn.hll.tools.base.mod.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Jas°
 * @since 2022-11-22
 */
@Validated
@RestController
@Api(tags = {"测试"})
@RequestMapping("test")
public class TestController {
    @Resource
    private ExTabService exTabService;

    @ApiOperation(value = "批处理")
    @ApiPrint(value = "批处理")
    @PostMapping(value = "batch")
    public JsonResult batch(@RequestBody List<Long> ids) {
        exTabService.batch(ids);
        return JsonResult.OK;
    }
}
