package cn.huolala.gm.demo.service.impl;

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
    public void saveBatch(List<Tab> list) {
        list.stream().mapToInt(tab -> tabMapper.insert(tab)).sum();
    }

    @Override
    public void modify(Tab tab) {
        if (Objects.isNull(tab.getId())) {
            tabMapper.insert(tab);
        } else {
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
        tab.setDeleteTag(true);
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
