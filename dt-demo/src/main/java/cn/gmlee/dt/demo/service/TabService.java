package cn.gmlee.dt.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import cn.gmlee.dt.demo.dao.entity.Tab;
import cn.gmlee.dt.demo.controller.vo.TabVo;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jas°
 * @since 2022-11-22
 */
public interface TabService extends IService<Tab> {
    /**
    * 批量保存.
    *
    * @param list the list
    */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    void saveBatch(List<TabVo> list);

    /**
    * 新增/修改 (根据ID).
    *
    * @param tabVo the tabVo
    * @return the tab
    */
    Tab modify(TabVo tabVo);

    /**
    * 批量修改 (根据ID).
    *
    * @param list the list
    * @return the int
    */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    void updateBatch(List<TabVo> list);

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
    * @param tabVo the tabVo
    * @return the list
    */
    List<Tab> listBy(TabVo tabVo);

    /**
    * 分页条件查询.
    *
    * @param page         the page
    * @param tabVo the tabVo
    * @return the list
    */
    IPage listPageBy(Page page, TabVo tabVo);
}
