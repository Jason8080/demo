package com.gm.demo.shard.service;

import com.github.pagehelper.Page;
import com.gm.demo.shard.dao.entity.Test;
import com.gm.demo.shard.dao.repository.TestRepository;
import com.gm.model.request.PageReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jason
 */
@Service
public class TestService {

    @Autowired
    TestRepository testRepository;

    public void save(Test test) {
    }

    public Page<Test> get(PageReq req) {
        return new Page();
    }

}
