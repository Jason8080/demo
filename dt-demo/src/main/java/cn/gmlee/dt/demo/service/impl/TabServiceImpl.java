package cn.gmlee.dt.demo.service.impl;

import cn.gmlee.dt.demo.dao.entity.Tab;
import cn.gmlee.dt.demo.controller.vo.TabVo;
import cn.gmlee.dt.demo.dao.mapper.TabMapper;
import cn.gmlee.dt.demo.service.TabService;

import cn.hll.tools.base.util.BeanUtil;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jas°
 * @since 2022-11-22
 */
@Service
public class TabServiceImpl extends ServiceImpl<TabMapper, Tab> implements TabService {

    @Resource
    TabMapper tabMapper;

    @Override
    public void saveBatch(List<TabVo> list) {
        list.stream().mapToInt(tabVo -> {
            Tab tab = BeanUtil.convert(tabVo, Tab.class);
            return tabMapper.insert(tab);
        }).sum();
    }

    @Override
    public Tab modify(TabVo tabVo) {
        Tab tab = BeanUtil.convert(tabVo, Tab.class);
        if (Objects.isNull(tab.getId())) {
            tabMapper.insert(tab);
        } else {
            tabMapper.updateById(tab);
        }
        return tab;
    }

    @Override
    public void updateBatch(List<TabVo> list) {
        list.stream().mapToInt(tabVo -> {
            Tab tab = BeanUtil.convert(tabVo, Tab.class);
            return tabMapper.updateById(tab);
        }).sum();
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
    public List<Tab> listBy(TabVo tabVo) {
        Tab tab = BeanUtil.convert(tabVo, Tab.class);
        return tabMapper.selectList(new QueryWrapper(tab));
    }

    @Override
    public IPage listPageBy(Page page, TabVo tabVo) {
        Tab tab = BeanUtil.convert(tabVo, Tab.class);
        return tabMapper.selectPage(page, new QueryWrapper(tab));
    }
}
