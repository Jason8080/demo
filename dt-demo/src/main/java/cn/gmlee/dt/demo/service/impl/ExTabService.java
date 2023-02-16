package cn.gmlee.dt.demo.service.impl;

import cn.gmlee.ct.client.anno.Ct;
import cn.gmlee.dt.demo.dao.mapper.TabMapper;
import cn.hll.tools.base.mod.HttpResult;
import cn.hll.tools.base.mod.JsonResult;
import cn.hll.tools.base.util.AssertUtil;
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
     * 事务验证.
     * <p>
     * 预期效果: 全部回滚.
     * 远程事务失效: 会导致 id=3 的数据被删除
     * 本地事务失效: 会导致 `首条` 记录被删除
     * </p>
     *
     * @param ids the ids
     */
    @Ct
    @Transactional(rollbackFor = Exception.class)
    public void batch(List<Long> ids) {
        // 远程调用
        HttpResult httpResult = HttpUtil.post("http://127.0.0.1:8080/tab/del", 3);
        AssertUtil.isTrue(httpResult.isOk(), httpResult.getErr());
        JsonResult jsonResult = httpResult.jsonResponseBody2bean(JsonResult.class);
        AssertUtil.isOk(jsonResult, jsonResult.getMsg());
        // 本地逻辑
        for (int i = 0; i < ids.size(); i++) {
            if (i == ids.size() - 1) {
                int ex = i / 0;
                System.out.println(ex);
            }
            tabMapper.deleteById(ids.get(i));
            log.info("已删除: {}", ids.get(i));
        }
    }
}
