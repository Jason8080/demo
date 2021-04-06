package com.gm.data.util.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gm.data.util.dao.entity.Tab;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * 案例表格表(Tab)表服务接口
 *
 * @author Jas°
 * @since 2021-03-29 20:43:57
 */
public interface TabService extends IService<Tab> {
    /**
     * 批量保存.
     *
     * @param list the list
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    void saveBatch(List<Tab> list);

    /**
     * 新增/修改 (根据ID).
     *
     * @param tab the file tab
     */
    void modify(Tab tab);

    /**
     * 批量修改 (根据ID).
     *
     * @param list the list
     * @return the int
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    void updateBatch(List<Tab> list);

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
     * @param tab the tab
     * @return the list
     */
    List<Tab> listBy(Tab tab);

    /**
     * 分页条件查询.
     *
     * @param page the page
     * @param tab  the tab
     * @return the list
     */
    IPage listPageBy(Page page, Tab tab);
}
