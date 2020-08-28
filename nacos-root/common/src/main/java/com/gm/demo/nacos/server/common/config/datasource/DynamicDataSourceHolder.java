package com.gm.demo.nacos.server.common.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Timi
 * @date 2020/8/20 12:14
 */
@Slf4j
public class DynamicDataSourceHolder {
    private static ThreadLocal<String> contextHolder = new ThreadLocal<>();
    public static final String DB_MASTER = "master";
    public static final String DB_SLAVE = "slave";
    private static final Logger log = LoggerFactory.getLogger(DynamicDataSourceHolder.class);
    public static String getDbType() {
        String db = contextHolder.get();
        if (db == null) {
            db = DB_MASTER;
        }
        log.info("数据源: " + db);
        return db;
    }
 
    public static void setDBType(String str) {
        contextHolder.set(str);
    }
 
    public static void clearDbType() {
        contextHolder.remove();
    }
}