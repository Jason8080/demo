package com.gm.demo.tools.sharding;

import cn.hll.tools.base.util.JsonUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gm.demo.tools.sharding.dao.entity.Tab;
import com.gm.demo.tools.sharding.dao.entity.User;
import com.gm.demo.tools.sharding.dao.mapper.TabMapper;
import com.gm.demo.tools.sharding.dao.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * The type User test.
 */
@ActiveProfiles("loc")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class ShardingJdbcTest {

    @Resource
    private TabMapper tabMapper;

    @Resource
    private UserMapper userMapper;

    /**
     * 动态切换数据.
     *
     * @throws Exception the exception
     */
    @Test
//    @Transactional
    @Rollback(false)
    // 开启事务的同时, 会提前获取连接, 保证事务内操作是同一个连接
    // 没有事务时, 只会在执行持久化操作时, 才会获取连接
    // 所以, 不开事务, 切了数据源, 就会使用新节点, 开了事务, 切换数据源则不受影响
    // 由此, 达到切换数据源不影响现有业务的需求.......
    public void add() throws Exception {
        Tab tab = new Tab();
        tab.setColumn1("username");
        tab.setStr("Jas.lee");
        tab.setDate(new Date());
        tab.setUserId(1L);
        tabMapper.insert(tab);
    }

    /**
     * 普通新增.
     *
     * @throws Exception the exception
     */
    @Test
    public void addUser() throws Exception {
        User user = new User();
        user.setName("Jas°");
        user.setGender(false);
        user.setBirthday(new Date());
        userMapper.insert(user);
    }

    /**
     * 普通查询.
     *
     * @throws Exception the exception
     */
    @Test
    public void getUser() throws Exception {
        LambdaQueryWrapper<User> qw = Wrappers.<User>lambdaQuery()
                .eq(User::getGender, false)
//                .between(User::getBirthday, LocalDateTimeUtil.offsetCurrent(-1, ChronoUnit.DAYS), new Date())
                ;
        List<User> users = userMapper.selectList(qw);
        System.out.println(JsonUtil.format(users));
    }

    /**
     * 分库分表查询.
     *
     * @throws Exception the exception
     */
    @Test
    public void get() throws Exception {
        LambdaQueryWrapper<Tab> qw = Wrappers.<Tab>lambdaQuery()
                .eq(Tab::getUserId, 1)
//                .between(Tab::getDate, LocalDateTimeUtil.offsetCurrent(-1, ChronoUnit.DAYS), new Date())
                ;List<Tab> tabs = tabMapper.selectList(qw);
        System.out.println(JsonUtil.format(tabs));
    }
}