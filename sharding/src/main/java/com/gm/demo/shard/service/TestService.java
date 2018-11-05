package com.gm.demo.shard.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gm.demo.shard.dao.entity.Test;
import com.gm.demo.shard.dao.mapper.TestMapper;
import com.gm.model.request.PageReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jason
 */
@Service
public class TestService {
    @Autowired
    TestMapper testMapper;

    public void save(Test test) {
        testMapper.insert(test);
    }

    public IPage<Test> get(PageReq req) {
        IPage<Test> page = testMapper.selectPage(new Page<Test>(req.getPageNo(), req.getPageSize()), new Wrapper<Test>() {
            @Override
            public Test getEntity() {
                return new Test();
            }

            public String getSqlSegment() {
                return "";
            }
        });
        return page;
    }

}
