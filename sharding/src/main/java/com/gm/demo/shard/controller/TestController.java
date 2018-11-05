package com.gm.demo.shard.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gm.demo.shard.dao.entity.Test;
import com.gm.demo.shard.service.TestService;
import com.gm.model.request.PageReq;
import com.gm.model.response.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jason
 */
@RestController("test")
@Api(tags = "演示控制器")
public class TestController {
    @Autowired
    TestService testService;

    @PostMapping("save")
    @ApiOperation(value = "保存数据", notes = "...", response = JsonResult.class)
    public JsonResult save(Test test){
        test.insert();
        return JsonResult.SUCCESS_;
    }

    @GetMapping("get")
    @ApiOperation(value = "获取数据", notes = "...", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "起始页", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页容量", example = "10"),
    })
    public JsonResult<Test> get(PageReq req){
        IPage<Test> page = testService.get(req);
        JsonResult<Test> result = new JsonResult(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
        return result;
    }
}
