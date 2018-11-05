package com.gm.demo.shard.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.gm.demo.shard.dao.entity.Test;

/**
 * @author Jason
 */
public interface TestMapper extends BaseMapper<Test> {
    /**
     * 666
     * @return
     */
    Page getAll();
}