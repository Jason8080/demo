package cn.gmlee.dt.demo.service.impl;

import cn.gmlee.dt.demo.dao.mapper.TabMapper;
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
        // TODO:
        // 本地逻辑
        for (int i = 0; i < ids.size(); i++){
            if(i == ids.size() - 1){
                int ex = i / 0;
                System.out.println(ex);
            }
            tabMapper.deleteById(ids.get(i));
        }
    }
}
