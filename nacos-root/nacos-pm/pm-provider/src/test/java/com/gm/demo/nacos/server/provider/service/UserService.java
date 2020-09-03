package com.gm.demo.nacos.server.provider.service;

import com.gm.demo.nacos.server.common.config.mp.ReadOnly;
import com.gm.demo.nacos.server.provider.dao.mapper.UserMapper;
import com.gm.demo.nacos.server.provider.dao.entity.User;
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
        return userMapper.selectList(null);
    }
}
