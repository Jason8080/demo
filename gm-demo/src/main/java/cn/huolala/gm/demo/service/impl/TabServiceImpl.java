package cn.huolala.gm.demo.service.impl;

import cn.gmlee.tools.base.mod.PageRequest;
import cn.gmlee.tools.base.mod.PageResponse;
import cn.gmlee.tools.base.util.BeanUtil;
import cn.huolala.gm.demo.controller.vo.TabVo;
import cn.huolala.gm.demo.dao.entity.Tab;
import cn.huolala.gm.demo.dao.mapper.TabMapper;
import cn.huolala.gm.demo.service.TabService;
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
 *  服务实现类
 * </p>
 *
 * @author Timi°
 * @since 2021-01-12
 */
@Service
public class TabServiceImpl extends ServiceImpl<TabMapper, Tab> implements TabService {

    @Resource
    TabMapper tabMapper;

    @Override
    public void saveBatch(List<TabVo> list) {
        list.stream().mapToInt(vo -> {
            Tab tab = BeanUtil.convert(vo, Tab.class);
            return tabMapper.insert(tab);
        }).sum();
    }

    @Override
    public void modify(TabVo vo) {
        Tab tab = BeanUtil.convert(vo, Tab.class);
        if (Objects.isNull(vo.getId())) {
            tabMapper.insert(tab);
        } else {
            tabMapper.updateById(tab);
        }
    }

    @Override
    public void updateBatch(List<TabVo> list) {
        list.stream().mapToInt(vo -> {
            Tab tab = BeanUtil.convert(vo, Tab.class);
            return tabMapper.updateById(tab);
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
        Tab tab = new Tab();
        tab.setId(id);
        tab.setDeleteTag(true);
        tabMapper.updateById(tab);
    }

    @Override
    public void logicDelByIds(Collection<Long> ids) {
        ids.forEach(id -> logicDelById(id));
    }

    @Override
    public List<TabVo> listBy(TabVo tab) {
        List<Tab> list = tabMapper.selectList(new QueryWrapper(tab));
        return list.stream().map(o -> BeanUtil.convert(o, TabVo.class)).collect(Collectors.toList());
    }

    @Override
    public PageResponse<TabVo> listPageBy(PageRequest pageRequest, TabVo tab) {
        // 1. 建议采用PageResponse对象封装分页对象: 简洁,但不强制
        Page page = new Page(pageRequest.current, pageRequest.size);
        IPage<Tab> selectPage = tabMapper.selectPage(page, new QueryWrapper(tab));
        // 2. 建议采用Vo展示数据: 隔离、简洁、非必需
        List<Tab> records = selectPage.getRecords();
        List<TabVo> list = records.stream().map(o -> BeanUtil.convert(o, TabVo.class)).collect(Collectors.toList());
        return new PageResponse(pageRequest, selectPage.getTotal(), list);
    }
}
