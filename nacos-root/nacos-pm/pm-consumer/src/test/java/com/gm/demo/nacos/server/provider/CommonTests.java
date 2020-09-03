package com.gm.demo.nacos.server.provider;
import com.gm.demo.nacos.server.provider.dao.entity.User;
import com.gm.demo.nacos.server.provider.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 测试
 *
 * @author Timi.lee
 * @date 2020/8/28 (周五)
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CommonTests {

    @Autowired
    private UserService userService;

    @Test
    public void testSelect() {
        List<User> userList = userService.selectList();
        userList.forEach(System.out::println);
    }
}
