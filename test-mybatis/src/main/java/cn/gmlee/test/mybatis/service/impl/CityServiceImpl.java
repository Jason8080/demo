package cn.gmlee.test.mybatis.service.impl;

import cn.gmlee.test.mybatis.dao.entity.City;
import cn.gmlee.test.mybatis.controller.vo.CityVo;
import cn.gmlee.test.mybatis.dao.mapper.CityMapper;
import cn.gmlee.test.mybatis.service.CityService;


import cn.hll.tools.base.util.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 城市字典表 服务实现类
 * </p>
 *
 * @author Jas°
 * @since 2021-08-27
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {

    @Resource
    CityMapper cityMapper;

    @Override
    public void saveBatch(List<CityVo> list) {
        list.stream().mapToInt(cityVo -> {
            City city = BeanUtil.convert(cityVo, City.class);
            return cityMapper.insert(city);
        }).sum();
    }

    @Override
    public void modify(CityVo cityVo) {
        City city = BeanUtil.convert(cityVo, City.class);
        if (Objects.isNull(city.getId())) {
            cityMapper.insert(city);
        } else {
            cityMapper.updateById(city);
        }
    }

    @Override
    public void updateBatch(List<CityVo> list) {
        list.stream().mapToInt(cityVo -> {
            City city = BeanUtil.convert(cityVo, City.class);
            return cityMapper.updateById(city);
        }).sum();
    }

    @Override
    public List<City> listBy(CityVo cityVo) {
        City city = BeanUtil.convert(cityVo, City.class);
        return cityMapper.selectList(new QueryWrapper(city));
    }

    @Override
    public IPage listPageBy(Page page, CityVo cityVo) {
        City city = BeanUtil.convert(cityVo, City.class);
        return cityMapper.selectPage(page, new QueryWrapper(city));
    }
}
