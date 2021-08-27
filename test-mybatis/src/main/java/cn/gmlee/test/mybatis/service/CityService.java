package cn.gmlee.test.mybatis.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import cn.gmlee.test.mybatis.dao.entity.City;
import cn.gmlee.test.mybatis.controller.vo.CityVo;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 城市字典表 服务类
 * </p>
 *
 * @author Jas°
 * @since 2021-08-27
 */
public interface CityService extends IService<City> {
    /**
    * 批量保存.
    *
    * @param list the list
    */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    void saveBatch(List<CityVo> list);

    /**
    * 新增/修改 (根据ID).
    *
    * @param cityVo the cityVo
    */
    void modify(CityVo cityVo);

    /**
    * 批量修改 (根据ID).
    *
    * @param list the list
    * @return the int
    */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    void updateBatch(List<CityVo> list);

    /**
    * 条件查询.
    *
    * @param cityVo the cityVo
    * @return the list
    */
    List<City> listBy(CityVo cityVo);

    /**
    * 分页条件查询.
    *
    * @param page         the page
    * @param cityVo the cityVo
    * @return the list
    */
    IPage listPageBy(Page page, CityVo cityVo);
}
