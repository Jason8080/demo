package com.gm.data.util.dao.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gm.data.util.dao.entity.Tab;
import org.apache.ibatis.annotations.Param;

/**
 * 案例表格表(Tab)表数据库访问层
 *
 * @author Jas°
 * @since 2021-03-29 20:43:57
 */
public interface TabMapper extends BaseMapper<Tab> {

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Tab> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Tab> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Tab> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Tab> entities);

}

