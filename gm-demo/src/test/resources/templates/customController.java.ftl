package ${package.Controller};



import cn.huolala.common.base.mod.JsonResult;
import cn.huolala.common.base.mod.PageRequest;
import cn.huolala.common.cloud.fallback.ApiFallback;
import cn.huolala.common.logback.anno.ApiPrint;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Validated
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@Api(tags = {"${table.comment!} 前端控制器"})
@RequestMapping("<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
     @Resource
     ${table.serviceName} ${table.serviceName?uncap_first};

     @ApiOperation(value = "批量保存")
     @ApiPrint(value = "批量保存")
     @SentinelResource(value = "saveBatch", fallbackClass = ApiFallback.class)
     @PostMapping(value = "saveBatch")
     @ApiImplicitParams({
           @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
     })
     public JsonResult saveBatch(
           @RequestBody @NotNull(message = "数据是空") @Validated List<${entity}> vos
      ) {
           ${table.serviceName?uncap_first}.saveBatch(vos);
           return JsonResult.OK;
      }

      @ApiOperation(value = "保存")
      @ApiPrint(value = "保存")
      @SentinelResource(value = "save", fallbackClass = ApiFallback.class)
      @PostMapping(value = "save")
      @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
      })
      public JsonResult save(
            @RequestBody @Validated ${entity} vo
      ) {
            ${table.serviceName?uncap_first}.save(vo);
            return JsonResult.OK;
      }

      @ApiOperation(value = "新增/修改", notes = "有`主键`则修改反之新增")
      @ApiPrint(value = "新增/修改")
      @SentinelResource(value = "modify", fallbackClass = ApiFallback.class)
      @PostMapping(value = "modify")
      @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
      })
      public JsonResult modify(
            @RequestBody @Validated ${entity} vo
      ) {
            ${table.serviceName?uncap_first}.modify(vo);
            return JsonResult.OK;
      }

      @ApiOperation(value = "逻辑删除")
      @ApiPrint(value = "逻辑删除")
      @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
            @ApiImplicitParam(name = "id", value = "编号", dataType = "long", dataTypeClass = Long.class),
      })
      @SentinelResource(value = "logicDelById", fallbackClass = ApiFallback.class)
      @PostMapping(value = "logicDelById")
      public JsonResult logicDelById(
            @RequestBody @NotNull(message = "编号是空") Long id
      ) {
            ${table.serviceName?uncap_first}.logicDelById(id);
            return JsonResult.OK;
      }

      @ApiOperation(value = "获取单条")
      @ApiPrint(value = "获取单条")
      @SentinelResource(value = "getById", fallbackClass = ApiFallback.class)
      @GetMapping(value = "getById")
      @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
            @ApiImplicitParam(name = "id", value = "编号", paramType = "query", dataType = "long", dataTypeClass = Long.class),
      })
      public JsonResult getById(
            @NotNull(message = "编号是空") Long id
      ) {
            return JsonResult.OK.newly(${table.serviceName?uncap_first}.getById(id));
      }

      @ApiOperation(value = "获取列表")
      @ApiPrint(value = "获取列表")
      @SentinelResource(value = "listBy", fallbackClass = ApiFallback.class)
      @PostMapping(value = "listBy")
      @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
      })
      public JsonResult listBy(
            @RequestBody ${entity} vo
      ) {
            return JsonResult.OK.newly(${table.serviceName?uncap_first}.listBy(vo));
      }

      @ApiOperation(value = "分页查询")
      @ApiPrint(value = "分页查询")
      @SentinelResource(value = "listPageBy", fallbackClass = ApiFallback.class)
      @PostMapping(value = "listPageBy")
      @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "起始页", paramType = "query", dataType = "integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "size", value = "页数量", paramType = "query", dataType = "integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
      })
      public JsonResult listPageBy(
            PageRequest page, @RequestBody ${entity} vo
      ) {
            return JsonResult.OK.newly(${table.serviceName?uncap_first}.listPageBy(new Page(page.current, page.size), vo));
      }
}
</#if>
