package com.gm.data.util.datalog;

import cn.gmlee.tools.datalog.interceptor.AbstractDatalogInterceptorHandler;
import cn.gmlee.tools.mybatis.dao.IBatisDao;
import com.gm.data.util.dao.entity.MyLog;
import org.apache.ibatis.session.SqlSessionFactory;
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
    SqlSessionFactory sqlSessionFactory;

    Logger logger = LoggerFactory.getLogger(MyLogHandler.class);
    @Override
    public boolean commit(MyLog myLog) {
        IBatisDao<MyLog> iBatisDao = new IBatisDao<>(sqlSessionFactory);
        iBatisDao.execute(myLog, "cn.gmlee.tools.datalog.dao.mapper.DatalogMapper.insert");
        return true;
    }
}
