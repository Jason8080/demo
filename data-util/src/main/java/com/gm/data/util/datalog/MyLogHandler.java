package com.gm.data.util.datalog;

import cn.gmlee.tools.datalog.interceptor.AbstractDatalogInterceptorHandler;
import com.gm.data.util.dao.entity.MyLog;
import com.gm.data.util.dao.mapper.MyLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Jas°
 * @date 2021/4/6 (周二)
 */
@Component
public class MyLogHandler extends AbstractDatalogInterceptorHandler<MyLog> {

    @Resource
    MyLogMapper myLogMapper;

    Logger logger = LoggerFactory.getLogger(MyLogHandler.class);
    @Override
    public boolean commit(MyLog myLog) {
        myLogMapper.insert(myLog);
        return true;
    }
}
