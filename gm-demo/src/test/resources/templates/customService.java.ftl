package ${package.Service};

import cn.gmlee.tools.base.mod.PageRequest;
import cn.gmlee.tools.base.mod.PageResponse;
import cn.gmlee.tools.mysql.anno.ReadOnly;
import ${cfg.Vo}.${entity}Vo;
import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {
    /**
    * 批量保存.
    *
    * @param list the list
    */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    void saveBatch(List<${entity}Vo> list);

    /**
    * 新增/修改 (根据ID).
    *
    * @param ${entity?uncap_first}Vo the file ${entity?uncap_first}Vo
    */
    void modify(${entity}Vo ${entity?uncap_first}Vo);

    /**
    * 批量修改 (根据ID).
    *
    * @param list the list
    * @return the int
    */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    void updateBatch(List<${entity}Vo> list);

    /**
    * 逻辑删除 (根据ID).
    *
    * @param id the id
    */
    void logicDelById(Long id);

    /**
    * 批量逻辑删除 (根据ID).
    *
    * @param ids the ids
    */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    void logicDelByIds(Collection<Long> ids);

    /**
    * 条件查询.
    *
    * @param ${entity?uncap_first}Vo the ${entity?uncap_first}Vo
    * @return the list
    */
    @ReadOnly
    List<${entity}Vo> listBy(${entity}Vo ${entity?uncap_first}Vo);

    /**
    * 分页条件查询.
    *
    * @param pageRequest         the page
    * @param ${entity?uncap_first}Vo the ${entity?uncap_first}Vo
    * @return the page response
    */
    @ReadOnly
    PageResponse<${entity}Vo> listPageBy(PageRequest pageRequest, ${entity}Vo ${entity?uncap_first}Vo);
}
</#if>
