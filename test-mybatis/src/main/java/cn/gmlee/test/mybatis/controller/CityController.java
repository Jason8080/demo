package cn.gmlee.test.mybatis.controller;



import cn.hll.tools.base.anno.ApiPrint;
import cn.hll.tools.base.mod.JsonResult;
import cn.hll.tools.base.mod.PageRequest;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cn.gmlee.test.mybatis.service.CityService;
import cn.gmlee.test.mybatis.dao.entity.City;
import cn.gmlee.test.mybatis.controller.vo.CityVo;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 城市字典表 前端控制器
 * </p>
 *
 * @author Jas°
 * @since 2021-08-27
 */
@Validated
@RestController
@Api(tags = {"城市字典表 前端控制器"})
@RequestMapping("city")
public class CityController {
     @Resource
     CityService cityService;

     @ApiOperation(value = "批量保存")
     @ApiPrint(value = "批量保存")
     @PostMapping(value = "saveBatch")
     @ApiImplicitParams({
           @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
     })
     public JsonResult saveBatch(
           @RequestBody @NotNull(message = "数据是空") @Validated List<CityVo> vos
      ) {
           cityService.saveBatch(vos);
           return JsonResult.OK;
      }

      @ApiOperation(value = "保存")
      @ApiPrint(value = "保存")
      @PostMapping(value = "save")
      @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
      })
      public JsonResult save(
            @RequestBody @Validated City city
      ) {
            cityService.save(city);
            return JsonResult.OK;
      }

      @ApiOperation(value = "新增/修改", notes = "有`主键`则修改反之新增")
      @ApiPrint(value = "新增/修改")
      @PostMapping(value = "modify")
      @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
      })
      public JsonResult modify(
            @RequestBody @Validated CityVo vo
      ) {
            cityService.modify(vo);
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
            return JsonResult.OK.newly(cityService.getById(id));
      }

      @ApiOperation(value = "获取列表")
      @ApiPrint(value = "获取列表")
      @PostMapping(value = "listBy")
      @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
      })
      public JsonResult listBy(
            @RequestBody CityVo vo
      ) {
            return JsonResult.OK.newly(cityService.listBy(vo));
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
              PageRequest page, @RequestBody CityVo vo
      ) {
            return JsonResult.OK.newly(cityService.listPageBy(new Page(page.current, page.size), vo));
      }
}
