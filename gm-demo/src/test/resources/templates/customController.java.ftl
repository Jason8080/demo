package ${package.Controller};

import cn.gmlee.tools.base.entity.Id;
import cn.gmlee.tools.base.mod.JsonResult;
import cn.gmlee.tools.base.mod.PageRequest;
import cn.gmlee.tools.base.mod.PageResponse;
import cn.gmlee.tools.base.util.BeanUtil;
import cn.gmlee.tools.logback.anno.ApiPrint;
import ${cfg.Vo}.${entity}Vo;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(value = "saveBatch")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
    })
    public JsonResult saveBatch(
        @Validated @RequestBody @NotNull(message = "数据是空") List<${entity}Vo> vos
    ) {
        ${table.serviceName?uncap_first}.saveBatch(vos);
        return JsonResult.OK;
    }

    @ApiOperation(value = "保存")
    @ApiPrint(value = "保存")
    @PostMapping(value = "save")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
    })
    public JsonResult save(
        @Validated @RequestBody ${entity}Vo vo
    ) {
        ${entity} ${entity?uncap_first} = BeanUtil.convert(vo, ${entity}.class);
        tabService.save(${entity?uncap_first});
        return JsonResult.OK;
    }

    @ApiOperation(value = "新增/修改", notes = "有`主键`则修改反之新增")
    @ApiPrint(value = "新增/修改")
    @PostMapping(value = "modify")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
    })
    public JsonResult modify(
        @Validated @RequestBody ${entity}Vo vo
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
    @PostMapping(value = "logicDelById")
    public JsonResult logicDelById(
        // 注意: 此处为url传参 (可自行更改)
        @NotNull(message = "编号是空") Id id
    ) {
        ${table.serviceName?uncap_first}.logicDelById(id.id);
        return JsonResult.OK;
    }

    @ApiOperation(value = "获取单条")
    @ApiPrint(value = "获取单条")
    @GetMapping(value = "getById")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
        @ApiImplicitParam(name = "id", value = "编号", paramType = "query", dataType = "long", dataTypeClass = Long.class),
    })
    public JsonResult<${entity}Vo> getById(
        // 注意: 此处为url传参 (可自行更改)
        @NotNull(message = "编号是空") Id id
    ) {
        ${entity} ${entity?uncap_first} = ${table.serviceName?uncap_first}.getById(id.id);
        // 建议统一采用Vo展示数据: 文档简洁、可扩展、安全
        ${entity}Vo ${entity?uncap_first}Vo = BeanUtil.convert(${entity?uncap_first}, ${entity}Vo.class);
        return JsonResult.OK.newly(${entity?uncap_first}Vo);
    }

    @ApiOperation(value = "获取列表")
    @ApiPrint(value = "获取列表")
    @PostMapping(value = "listBy")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
    })
    public JsonResult<List<${entity}Vo>> listBy(
        @Validated @RequestBody ${entity}Vo vo
    ) {
        return JsonResult.OK.newly(${table.serviceName?uncap_first}.listBy(vo));
    }

    @ApiOperation(value = "分页查询")
    @ApiPrint(value = "分页查询")
    @PostMapping(value = "listPageBy")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "current", value = "起始页", paramType = "query", dataType = "integer", dataTypeClass = Integer.class),
        @ApiImplicitParam(name = "size", value = "页数量", paramType = "query", dataType = "integer", dataTypeClass = Integer.class),
        @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", dataType = "string", dataTypeClass = String.class),
    })
    public JsonResult<PageResponse<${entity}Vo>> listPageBy(
        PageRequest pageRequest, @Validated @RequestBody ${entity}Vo vo
    ) {
        return JsonResult.OK.newly(${table.serviceName?uncap_first}.listPageBy(pageRequest, vo));
    }
}
</#if>
