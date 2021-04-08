package com.gm.data.util.controller;


import cn.gmlee.tools.base.anno.ApiPrint;
import com.gm.data.util.dao.entity.Tab;
import com.gm.data.util.service.TabService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import cn.gmlee.tools.base.mod.JsonResult;
import cn.gmlee.tools.base.mod.PageRequest;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 案例表格表(Tab)表控制层
 *
 * @author Jas°
 * @since 2021-03-29 20:43:58
 */
@RestController
@RequestMapping("tab")
@Api(tags = {"案例表格表 前端控制器"})
public class TabController {

    @Resource
    private TabService tabService;

    @ApiOperation(value = "批量保存")
    @ApiPrint(value = "批量保存")
    @PostMapping(value = "saveBatch")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
    })
    public JsonResult saveBatch(
            @RequestBody @NotNull(message = "数据是空") @Validated List<Tab> vos
    ) {
        tabService.saveBatch(vos);
        return JsonResult.OK;
    }

    @ApiOperation(value = "保存")
    @ApiPrint(value = "保存")
    @PostMapping(value = "save")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
    })
    public JsonResult save(
            @RequestBody @Validated Tab vo
    ) {
        tabService.save(vo);
        return JsonResult.OK;
    }

    @ApiOperation(value = "新增/修改", notes = "有`主键`则修改反之新增")
    @ApiPrint(value = "新增/修改")
    @PostMapping(value = "modify")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
    })
    public JsonResult modify(
            @RequestBody @Validated Tab vo
    ) {
        tabService.modify(vo);
        return JsonResult.OK;
    }

    @ApiOperation(value = "逻辑删除")
    @ApiPrint(value = "逻辑删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
            @ApiImplicitParam(name = "id", value = "编号", dataType = "long", dataTypeClass = Long.class),
    })
    @PostMapping(value = "logicDelById")
    public JsonResult logicDelById(
            @RequestBody @NotNull(message = "编号是空") Long id
    ) {
        tabService.logicDelById(id);
        return JsonResult.OK;
    }

    @ApiOperation(value = "获取单条")
    @ApiPrint(value = "获取单条")
    @GetMapping(value = "getById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
            @ApiImplicitParam(name = "id", value = "编号", paramType = "query", dataType = "long", dataTypeClass = Long.class),
    })
    public JsonResult getById(
            @NotNull(message = "编号是空") Long id
    ) {
        return JsonResult.OK.newly(tabService.getById(id));
    }

    @ApiOperation(value = "获取列表")
    @ApiPrint(value = "获取列表")
    @PostMapping(value = "listBy")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
    })
    public JsonResult listBy(
            @RequestBody Tab vo
    ) {
        return JsonResult.OK.newly(tabService.listBy(vo));
    }

    @ApiOperation(value = "分页查询")
    @ApiPrint(value = "分页查询")
    @PostMapping(value = "listPageBy")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "起始页", paramType = "query", dataType = "integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "size", value = "页数量", paramType = "query", dataType = "integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
    })
    public JsonResult listPageBy(
            PageRequest page, @RequestBody Tab vo
    ) {
        return JsonResult.OK.newly(tabService.listPageBy(new Page(page.current, page.size), vo));
    }
}
