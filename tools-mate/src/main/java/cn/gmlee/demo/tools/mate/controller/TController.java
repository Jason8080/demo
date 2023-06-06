package cn.gmlee.demo.tools.mate.controller;


import cn.gmlee.demo.tools.mate.controller.vo.TVo;
import cn.gmlee.demo.tools.mate.dao.entity.T;
import cn.gmlee.demo.tools.mate.dao.mapper.TMapper;
import cn.gmlee.tools.base.anno.ApiPrint;
import cn.gmlee.tools.base.mod.JsonResult;
import cn.gmlee.tools.base.mod.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jas°
 * @since 2023-06-02
 */
@Validated
@RestController
@Api(tags = {" 前端控制器"})
@RequestMapping("t")
public class TController {
     @Resource
     TMapper tMapper;

      @ApiOperation(value = "保存")
      @ApiPrint(value = "保存")
      @PostMapping(value = "save")
      @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
      })
      public JsonResult save(
            @RequestBody @Validated T t
      ) {
            tMapper.insert(t);
            return JsonResult.OK;
      }

      @ApiOperation(value = "保存List")
      @ApiPrint(value = "保存List")
      @PostMapping(value = "saveList")
      @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
      })
      public JsonResult saveList(
              @RequestBody @Validated List<String> code
              ) {
            tMapper.insertList(code);
            return JsonResult.OK;
      }

      @ApiOperation(value = "保存InteriorList")
      @ApiPrint(value = "保存InteriorList")
      @PostMapping(value = "saveInteriorList")
      @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
      })
      public JsonResult saveInteriorList(
              @RequestBody @Validated TVo vo
              ) {
            tMapper.insertInteriorList(vo);
            return JsonResult.OK;
      }

      @ApiOperation(value = "保存String")
      @ApiPrint(value = "保存String")
      @PostMapping(value = "saveString")
      @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
      })
      public JsonResult saveString(
            @RequestBody @Validated T t
      ) {
            tMapper.insertString(t.getCode(), "哟呵");
            return JsonResult.OK;
      }

      @ApiOperation(value = "编码查询")
      @ApiPrint(value = "编码查询")
      @PostMapping(value = "encodeQuery")
      @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "起始页", paramType = "query", dataType = "integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "size", value = "页数量", paramType = "query", dataType = "integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
      })
      public JsonResult encodeQuery(@RequestBody TVo vo
      ) {
            return JsonResult.OK.newly(tMapper.selectVo(vo));
      }

      @ApiOperation(value = "分页查询")
      @ApiPrint(value = "分页查询")
      @PostMapping(value = "listPageBy")
      @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "起始页", paramType = "query", dataType = "integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "size", value = "页数量", paramType = "query", dataType = "integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
      })
      public JsonResult listPageBy(@RequestBody T vo
      ) {
            return JsonResult.OK.newly(tMapper.select(vo));
      }
}
