package cn.huolala.gm.demo.controller;


import cn.gmlee.tools.base.entity.Id;
import cn.gmlee.tools.base.mod.JsonResult;
import cn.gmlee.tools.base.mod.PageRequest;
import cn.gmlee.tools.base.mod.PageResponse;
import cn.gmlee.tools.base.util.BeanUtil;
import cn.gmlee.tools.logback.anno.ApiPrint;
import cn.huolala.gm.demo.controller.vo.TabVo;
import cn.huolala.gm.demo.dao.entity.Tab;
import cn.huolala.gm.demo.service.TabService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Timi°
 * @since 2021-01-12
 */
@Validated
@RestController
@Api(tags = {" 前端控制器"})
@RequestMapping("tab")
public class TabController {
    @Resource
    TabService tabService;

    @ApiOperation(value = "批量保存")
    @ApiPrint(value = "批量保存")
    @PostMapping(value = "saveBatch")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
    })
    public JsonResult saveBatch(
            @Validated @RequestBody @NotNull(message = "数据是空") List<TabVo> vos
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
            @Validated @RequestBody TabVo vo
    ) {
        Tab tab = BeanUtil.convert(vo, Tab.class);
        tabService.save(tab);
        return JsonResult.OK;
    }

    @ApiOperation(value = "新增/修改", notes = "有`主键`则修改反之新增")
    @ApiPrint(value = "新增/修改")
    @PostMapping(value = "modify")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
    })
    public JsonResult modify(
            @Validated @RequestBody TabVo vo
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
            // 注意: 此处为url传参 (可自行更改)
            @NotNull(message = "编号是空") Id id
    ) {
        tabService.logicDelById(id.id);
        return JsonResult.OK;
    }

    @ApiOperation(value = "获取单条")
    @ApiPrint(value = "获取单条")
    @GetMapping(value = "getById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
            @ApiImplicitParam(name = "id", value = "编号", paramType = "query", dataType = "long", dataTypeClass = Long.class),
    })
    public JsonResult<TabVo> getById(
            // 注意: 此处为url传参 (可自行更改)
            @NotNull(message = "编号是空") Id id
    ) {
        Tab tab = tabService.getById(id.id);
        // 建议统一采用Vo展示数据: 文档简洁、可扩展、安全
        TabVo vo = BeanUtil.convert(tab, TabVo.class);
        return JsonResult.OK.newly(vo);
    }

    @ApiOperation(value = "获取列表")
    @ApiPrint(value = "获取列表")
    @PostMapping(value = "listBy")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
    })
    public JsonResult<List<TabVo>> listBy(
            @Validated @RequestBody TabVo vo
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
    public JsonResult<PageResponse<TabVo>> listPageBy(
            PageRequest pageRequest, @Validated @RequestBody TabVo vo
    ) {
        return JsonResult.OK.newly(tabService.listPageBy(pageRequest, vo));
    }
}
