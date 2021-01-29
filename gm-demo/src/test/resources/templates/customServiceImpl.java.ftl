package ${package.ServiceImpl};

import cn.gmlee.tools.base.mod.PageRequest;
import cn.gmlee.tools.base.mod.PageResponse;
import cn.gmlee.tools.base.util.BeanUtil;
import ${package.Vo}.${vo};
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Resource
    ${table.mapperName} ${table.mapperName?uncap_first};

    @Override
    public void saveBatch(List<${vo}> list) {
        list.stream().mapToInt(vo -> {
            ${entity} ${entity?uncap_first} = BeanUtil.convert(vo, ${entity}.class);
            return tabMapper.insert(${entity?uncap_first});
        }).sum();
    }

    @Override
    public void modify(${vo} ${vo?uncap_first}) {
        ${entity} ${entity?uncap_first} = BeanUtil.convert(${vo?uncap_first}, ${entity}.class);
        if (Objects.isNull(${vo?uncap_first}.getId())) {
            tabMapper.insert(${entity?uncap_first});
        } else {
            tabMapper.updateById(${entity?uncap_first});
        }
    }

    @Override
    public void updateBatch(List<${vo}> list) {
        list.stream().mapToInt(vo -> {
            ${entity} ${entity?uncap_first} = BeanUtil.convert(vo, ${entity}.class);
            return tabMapper.updateById(${entity?uncap_first});
        }).sum();
    }

    /**
    * 添加 logic-delete-field=deleteTag 之后改为修改deleteTag=1
    *  -> tabMapper.deleteById(id);
    * 建议 take notes by oneself, as follows
    * @param id the id
    */
    @Override
    public void logicDelById(Long id) {
        ${entity} ${entity?uncap_first} = new ${entity}();
        ${entity?uncap_first}.setId(id);
        ${entity?uncap_first}.setDeleteTag(true);
        ${table.mapperName?uncap_first}.updateById(${entity?uncap_first});
    }

    @Override
    public void logicDelByIds(Collection<Long> ids) {
        ids.forEach(id -> logicDelById(id));
    }

    @Override
    public List<${vo}> listBy(${vo} ${vo?uncap_first}) {
        List<${entity}> list = tabMapper.selectList(new QueryWrapper(${vo?uncap_first}));
        return list.stream().map(o -> BeanUtil.convert(o, ${vo}.class)).collect(Collectors.toList());
    }

    @Override
    public PageResponse<${vo}> listPageBy(PageRequest pageRequest, ${vo} ${vo?uncap_first}) {
        // 1. 建议采用PageResponse对象封装分页对象: 简洁,但不强制
        Page page = new Page(pageRequest.current, pageRequest.size);
        IPage<${entity}> selectPage = tabMapper.selectPage(page, new QueryWrapper(${vo?uncap_first}));
        // 2. 建议采用Vo展示数据: 隔离、简洁、非必需
        List<${entity}> records = selectPage.getRecords();
        List<${vo}> list = records.stream().map(o -> BeanUtil.convert(o, ${vo}.class)).collect(Collectors.toList());
        return new PageResponse(pageRequest, selectPage.getTotal(), list);
    }
}
</#if>
