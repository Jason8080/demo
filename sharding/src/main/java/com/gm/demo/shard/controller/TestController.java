package com.gm.demo.shard.controller;

import com.dangdang.ddframe.rdb.sharding.id.generator.IdGenerator;
import com.gm.demo.shard.dao.entity.Test;
import com.gm.demo.shard.dao.repository.TestRepository;
import com.gm.demo.shard.service.TestService;
import com.gm.model.request.PageReq;
import com.gm.model.response.JsonResult;
import io.swagger.annotations.*;
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
    @Autowired
    TestRepository testRepository;
    @Autowired
    private IdGenerator idGenerator;

    @PostMapping("add")
    @ApiOperation(value = "添加数据", notes = "...", response = JsonResult.class)
    public JsonResult add(Test test){
        test.setUserId(idGenerator.generateId().longValue());
        test.setOrderId(idGenerator.generateId().longValue());
        testRepository.save(test);
        return JsonResult.SUCCESS_;
//
//        for (int i = 0; i < 10; i++) {
//            test = new Test();
//            test.setUserId((long) i);
//            test.setOrderId((long) i);
//            testRepository.save(test);
//        }
//        for (int i = 10; i < 20; i++) {
//            test = new Test();
//            test.setUserId((long) i + 1);
//            test.setOrderId((long) i);
//            testRepository.save(test);
//        }
    }

    @GetMapping("get")
    @ApiOperation(value = "获取数据", notes = "...", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "起始页", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页容量", example = "10"),
    })
    public JsonResult get(PageReq req){
        Iterable<Test> all = testRepository.findAll();
        return JsonResult.as(all);
    }
}
