package cn.huolala.gm.demo.service;

import cn.gmlee.tools.base.mod.PageRequest;
import cn.gmlee.tools.base.mod.PageResponse;
import cn.gmlee.tools.mysql.anno.ReadOnly;
import cn.huolala.gm.demo.controller.vo.TabVo;
import cn.huolala.gm.demo.dao.entity.Tab;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Timi°
 * @since 2021-01-12
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
    * @param tab the file tab
    */
    void modify(TabVo tab);

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
    void logicDelById(@NotNull @NotNull(
            message = "主键是空"
    ) Long id);

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
    @ReadOnly
    List<TabVo> listBy(TabVo tab);

    /**
    * 分页条件查询.
    *
    * @param pageRequest         the page
    * @param tab the tab
    * @return the list
    */
    @ReadOnly
    PageResponse<TabVo> listPageBy(PageRequest pageRequest, TabVo tab);
}
