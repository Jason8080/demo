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
import java.util.stream.Collectors;

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
        List<Map<String, Object>> oldsData = JdbcUtil.exec(
                dataSource,
                myLog.getDatalogSelectSql()
        );
        List<Map<String, Object>> commentMap = JdbcUtil.exec(
                dataSource,
                String.format("show full columns from %s", myLog.getDataTable())
        );
        Map<String, Object> fieldCommentMap = commentMap.stream().collect(Collectors.toMap(map -> map.get("Field").toString(), map -> map.get("Comment")));
        System.out.println(fieldCommentMap);
        System.out.println(oldsData);
        return true;
    }
}
