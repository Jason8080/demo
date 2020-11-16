package com.gm.demo.canal.monitor.handler;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Timi.lee
 * @date 2020/11/13 (周五)
 */
@Component
public class CanalClient implements InitializingBean {

    private static final int CANAL_BATCH_SIZE = 1000;

    public static void start() {
        String zkHost = "127.0.0.1:2181";

        String confName = "dev_db";

        String canalUsername = "";

        String canalPassword = "";

        // 每次数据的偏移量

        long batchId = 0;

        // 创建链接

        CanalConnector connector = CanalConnectors.newClusterConnector(zkHost, confName, canalUsername, canalPassword);

        // 外层死循环：

        // 在canal节点宕机后，抛出异常，

        // 等待zookeeper对canal处理机的切换，

        // 切换完后，继续创建连接处理数据

        while (true) {
            try {
                connector.connect();

                connector.subscribe(".*\\..*");

                connector.rollback();

                // 内层死循环:

                // 按频率实时监听数据变化，

                // 一旦收到变化数据，立即做消费处理，并ack

                // 考虑消费速度，可以考虑异步处理，并ack

                while (true) {
                    // 获取指定数量的数据

                    Message message = connector.getWithoutAck(CANAL_BATCH_SIZE);

                    batchId = message.getId();

                    int size = message.getEntries().size();

                    // 偏移量不等于-1 或者 获取的数据条数不为0 时，认为拿到消息，并处理

                    if (batchId == -1 || size == 0) {
                        // 200ms 拉一次变动数据

                        Thread.sleep(200);
                        // 提交确认
                        connector.ack(batchId);

                    } else {
                        printEntry(message.getEntries());
                        // 提交确认
                        connector.ack(batchId);

                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
                // 处理失败, 按偏移量回滚数据
                connector.rollback(batchId);
            } finally {
                // 关闭连接
                connector.disconnect();

            }

        }

    }

    /**
     * 打印canal server解析binlog获得的实体类信息
     */
    private static void printEntry(List<CanalEntry.Entry> entrys) {
        for (CanalEntry.Entry entry : entrys) {
            if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN || entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
                //开启/关闭事务的实体类型，跳过
                continue;
            }
            //RowChange对象，包含了一行数据变化的所有特征
            //比如isDdl 是否是ddl变更操作 sql 具体的ddl sql beforeColumns afterColumns 变更前后的数据字段等等
            CanalEntry.RowChange rowChage;
            try {
                rowChage = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(), e);
            }
            //获取操作类型：insert/update/delete类型
            CanalEntry.EventType eventType = rowChage.getEventType();
            //打印Header信息
            System.out.println(String.format("================》; binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                    eventType));
            //判断是否是DDL语句
            if (rowChage.getIsDdl()) {
                System.out.println("================》;isDdl: true,sql:" + rowChage.getSql());
            }
            //获取RowChange对象里的每一行数据，打印出来
            for (CanalEntry.RowData rowData : rowChage.getRowDatasList()) {
                //如果是删除语句
                if (eventType == CanalEntry.EventType.DELETE) {
                    printColumn(rowData.getBeforeColumnsList());
                    //如果是新增语句
                } else if (eventType == CanalEntry.EventType.INSERT) {
                    printColumn(rowData.getAfterColumnsList());
                    //如果是更新的语句
                } else {
                    //变更前的数据
                    System.out.println("------->; before");
                    printColumn(rowData.getBeforeColumnsList());
                    //变更后的数据
                    System.out.println("------->; after");
                    printColumn(rowData.getAfterColumnsList());
                }
            }
        }
    }

    private static void printColumn(List<CanalEntry.Column> columns) {
        for (CanalEntry.Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        start();
    }
}