package com.gm.data.util.datalog;

import cn.gmlee.tools.base.util.JdbcUtil;
import cn.gmlee.tools.datalog.interceptor.AbstractDatalogInterceptorHandler;
import com.gm.data.util.dao.entity.MyLog;
import com.gm.data.util.dao.mapper.MyLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @author Jas°
 * @date 2021/4/6 (周二)
 */
@Component
public class MyLogHandler extends AbstractDatalogInterceptorHandler<MyLog> {

    @Resource
    MyLogMapper myLogMapper;

    @Resource
    DataSource dataSource;

    Logger logger = LoggerFactory.getLogger(MyLogHandler.class);

    @Override
    public boolean commit(MyLog myLog) {
        List<Map<String, Object>> result = JdbcUtil.exec(
                dataSource,
                myLog.getDatalogSelectSql()
        );
        System.out.println(result);
        return true;
    }
}
