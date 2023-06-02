package cn.gmlee.demo.tools.mate.dao.mapper;

import cn.gmlee.demo.tools.mate.dao.entity.T;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Jas °
 * @since 2023 -06-02
 */
public interface TMapper extends Mapper<T> {

    /**
     * Insert string.
     *
     * @param code the code
     */
    @Insert("insert into t(`code`) values(#{code})")
    void insertString(@Param("code") String code);
}
