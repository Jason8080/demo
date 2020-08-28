package com.gm.demo.nacos.server.common.service;

import com.gm.demo.nacos.server.common.config.mp.ReadOnly;
import com.gm.demo.nacos.server.common.mapper.UserMapper;
import com.gm.demo.nacos.server.common.mapper.entity.User;
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
