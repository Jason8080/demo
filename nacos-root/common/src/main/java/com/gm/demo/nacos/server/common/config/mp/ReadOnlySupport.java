package com.gm.demo.nacos.server.common.config.mp;

import com.gm.demo.nacos.server.common.config.datasource.DynamicDataSourceHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ReadOnlySupport implements Ordered {
    private static final Logger log = LoggerFactory.getLogger(ReadOnlySupport.class);

    @Around("@annotation(readOnly)")
    public Object setRead(ProceedingJoinPoint joinPoint, ReadOnly readOnly) throws Throwable {
        try {
            DynamicDataSourceHolder.setDBType(DynamicDataSourceHolder.DB_SLAVE);
            return joinPoint.proceed();
        } finally {
            //清楚DbType一方面为了避免内存泄漏，更重要的是避免对后续在本线程上执行的操作产生影响
            DynamicDataSourceHolder.clearDbType();
            log.info("清除threadLocal");
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}