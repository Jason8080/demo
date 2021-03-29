package com.gm.data.util.mapper;

import com.gm.data.util.entity.Tab;
import org.apache.ibatis.annotations.Update;

/**
 * The interface Tab mapper.
 *
 * @author Jas °
 * @date 2021 /3/29 (周一)
 */
public interface TabMapper {
    /**
     * Update by id int.
     *
     * @param tab the tab
     * @return the int
     */
    @Update("update t_pos_tab set str=#{str} where id=#{id}")
    int updateById(Tab tab);
}
