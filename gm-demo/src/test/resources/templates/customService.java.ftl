package ${package.Service};

import cn.gmlee.tools.base.mod.PageRequest;
import cn.gmlee.tools.base.mod.PageResponse;
import cn.gmlee.tools.mysql.anno.ReadOnly;
import ${package.Vo}.${vo};
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
    void saveBatch(List<${vo}> list);

    /**
    * 新增/修改 (根据ID).
    *
    * @param ${vo?uncap_first} the file ${vo?uncap_first}
    */
    void modify(${vo} ${vo?uncap_first});

    /**
    * 批量修改 (根据ID).
    *
    * @param list the list
    * @return the int
    */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    void updateBatch(List<${vo}> list);

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
    * @param ${vo?uncap_first} the ${vo?uncap_first}
    * @return the list
    */
    @ReadOnly
    List<${vo}> listBy(${vo} ${vo?uncap_first});

    /**
    * 分页条件查询.
    *
    * @param pageRequest         the page
    * @param ${vo?uncap_first} the ${vo?uncap_first}
    * @return the page response
    */
    @ReadOnly
    PageResponse<${vo}> listPageBy(PageRequest pageRequest, ${vo} ${vo?uncap_first});
}
</#if>
