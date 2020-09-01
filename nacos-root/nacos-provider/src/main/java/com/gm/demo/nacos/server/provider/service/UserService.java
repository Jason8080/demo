package com.gm.demo.nacos.server.provider.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gm.demo.nacos.server.common.config.mp.ReadOnly;
import com.gm.demo.nacos.server.provider.mapper.UserMapper;
import com.gm.demo.nacos.server.provider.mapper.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Timi.lee
 * @date 2020/8/28 (周五)
 */
@Service
public class UserService {


    @Autowired
    UserMapper userMapper;

    @ReadOnly
    public List<User> selectList(){
        Page page = new Page(1, 2);
        IPage iPage = userMapper.selectPage(page, null);
        return iPage.getRecords();
    }

    @ReadOnly
    public IPage<User> selectPage(){
        Page page = new Page(1, 2);
        return userMapper.selectPage(page, null);
    }
}
