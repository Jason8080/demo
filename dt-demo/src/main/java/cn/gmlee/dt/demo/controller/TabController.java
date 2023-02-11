package cn.gmlee.dt.demo.controller;



import cn.hll.tools.base.mod.JsonResult;
import cn.hll.tools.base.mod.PageRequest;
import cn.hll.tools.base.anno.ApiPrint;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cn.gmlee.dt.demo.service.TabService;
import cn.gmlee.dt.demo.dao.entity.Tab;
import cn.gmlee.dt.demo.controller.vo.TabVo;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jas°
 * @since 2022-11-22
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
           @RequestBody @NotNull(message = "数据是空") @Validated List<TabVo> vos
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
            @RequestBody @Validated Tab tab
      ) {
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
            @RequestBody @Validated TabVo vo
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

      @Transactional(timeout = 3)
      @ApiOperation(value = "物理删除")
      @ApiPrint(value = "物理删除")
      @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
            @ApiImplicitParam(name = "id", value = "编号", dataType = "long", dataTypeClass = Long.class),
      })
      @PostMapping(value = "del")
      public JsonResult del(
            @RequestBody @NotNull(message = "编号是空") Long id
      ) {
            tabService.removeById(id);
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
            @RequestBody TabVo vo
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
            PageRequest page, @RequestBody TabVo vo
      ) {
            return JsonResult.OK.newly(tabService.listPageBy(new Page(page.current, page.size), vo));
      }
}
