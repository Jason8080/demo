package cn.gmlee.dt.demo.service.impl;

import cn.gmlee.dt.demo.dao.mapper.TabMapper;
import cn.hll.tools.base.mod.HttpResult;
import cn.hll.tools.base.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jas °
 * @since 2022 -11-22
 */
@Service
public class ExTabService {

    private static final Logger log = LoggerFactory.getLogger(ExTabService.class);

    @Resource
    private TabMapper tabMapper;


    /**
     * Batch.
     *
     * @param ids the ids
     */
    @Transactional(rollbackFor = Exception.class)
    public void batch(List<Long> ids) {
        // 远程调用
        HttpResult httpResult = HttpUtil.post("http://127.0.0.1:8080/tab/del", 3);
        System.out.println(httpResult.byteResponseBody2String());
        // 本地逻辑
        for (int i = 0; i < ids.size(); i++){
            if(i == ids.size() - 1){
                int ex = i / 0;
                System.out.println(ex);
            }
            tabMapper.deleteById(ids.get(i));
            log.info("已删除: {}", ids.get(i));
        }
    }
}
