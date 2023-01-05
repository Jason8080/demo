package com.gm.demo.tools.sharding;

import cn.hll.tools.base.util.JsonUtil;
import cn.hll.tools.base.util.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gm.demo.tools.sharding.dao.entity.Tab;
import com.gm.demo.tools.sharding.dao.mapper.TabMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.temporal.ChronoUnit;
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

    /**
     * 分库分表新增.
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
     * 分库分表查询.
     *
     * @throws Exception the exception
     */
    @Test
    public void get() throws Exception {
        Tab query = new Tab();
        query.setUserId(1L);
//        query.setDate(new Date());
        QueryWrapper<Tab> qw = new QueryWrapper(query);
        qw.between("date", LocalDateTimeUtil.offsetCurrent(-1, ChronoUnit.DAYS), new Date());
        List<Tab> tabs = tabMapper.selectList(qw);
        System.out.println(JsonUtil.format(tabs));
    }
}