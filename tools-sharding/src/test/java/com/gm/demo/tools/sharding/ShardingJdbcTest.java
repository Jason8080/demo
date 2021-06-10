package com.gm.demo.tools.sharding;

import cn.gmlee.tools.base.util.JsonUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gm.demo.tools.sharding.dao.entity.Tab;
import com.gm.demo.tools.sharding.dao.mapper.TabMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
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
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShardingJdbcTest {

    @LocalServerPort
    private int port;

    @Resource
    private TabMapper tabMapper;

    /**
     * Add.
     *
     * @throws Exception the exception
     */
    @Test
    public void add() throws Exception {
        Tab tab = new Tab();
        tab.setColumn1("username");
        tab.setStr("Jas.lee");
        tab.setDate(new Date());
        tab.setUserId(1L);
        tabMapper.insert(tab);
    }

    /**
     * Get.
     *
     * @throws Exception the exception
     */
    @Test
    public void get() throws Exception {
        QueryWrapper<Tab> qw = new QueryWrapper();
        List<Tab> tabs = tabMapper.selectList(qw);
        System.out.println(JsonUtil.format(tabs));
    }
}