package cn.gmlee.demo.tools.mate.dao.mapper;

import cn.gmlee.demo.tools.mate.dao.entity.T;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Jas °
 * @since 2023 -06-02
 */
public interface TMapper extends Mapper<T> {
    void insertString(@Param("code") String... code);
    void insertList(@Param("code") List<String> code);
}
