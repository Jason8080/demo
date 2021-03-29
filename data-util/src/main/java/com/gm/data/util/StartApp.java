package com.gm.data.util;

import com.gm.data.util.entity.Tab;
import com.gm.data.util.mapper.TabMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * The type Start app.
 *
 * @author Jas °
 * @date 2021 /3/29 (周一)
 */
public class StartApp {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TabMapper tabMapper = sqlSession.getMapper(TabMapper.class);
        Tab tab = new Tab();
        tab.setId(3L);
        tab.setStr(""+System.currentTimeMillis());
        int i = tabMapper.updateById(tab);
        sqlSession.commit();
        sqlSession.close();
        inputStream.close();
    }
}
