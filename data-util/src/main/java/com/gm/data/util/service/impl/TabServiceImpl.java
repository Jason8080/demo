package com.gm.data.util.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gm.data.util.dao.entity.Tab;
import com.gm.data.util.dao.mapper.TabMapper;
import com.gm.data.util.service.TabService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * 案例表格表(Tab)表服务实现类
 *
 * @author Jas°
 * @since 2021-03-29 20:43:57
 */
@Service("tabService")
public class TabServiceImpl extends ServiceImpl<TabMapper, Tab> implements TabService {

    @Resource
    TabMapper tabMapper;

    @Override
    public void saveBatch(List<Tab> list) {
        list.stream().mapToInt(tab -> tabMapper.insert(tab)).sum();
    }

    @Override
    @Transactional
    public void modify(Tab tab) {
        if (Objects.isNull(tab.getId())) {
            tabMapper.insert(tab);
        } else {
            Tab query = new Tab();
            query.setId(tab.getId());
            tabMapper.selectList(new QueryWrapper(query));
            tabMapper.updateById(tab);
        }
    }

    @Override
    public void updateBatch(List<Tab> list) {
        list.stream().mapToInt(tab -> tabMapper.updateById(tab)).sum();
    }

    @Override
    public void logicDelById(Long id) {
        Tab tab = new Tab();
        tab.setId(id);
        tab.setDel(true);
        tabMapper.updateById(tab);
    }

    @Override
    public void logicDelByIds(Collection<Long> ids) {
        ids.forEach(id -> logicDelById(id));
    }

    @Override
    public List<Tab> listBy(Tab tab) {
        return tabMapper.selectList(new QueryWrapper(tab));
    }

    @Override
    public IPage listPageBy(Page page, Tab tab) {
        return tabMapper.selectPage(page, new QueryWrapper(tab));
    }
}
