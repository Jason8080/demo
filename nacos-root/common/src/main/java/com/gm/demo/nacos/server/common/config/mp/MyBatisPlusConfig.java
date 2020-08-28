package com.gm.demo.nacos.server.common.config.mp;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.gm.demo.nacos.server.common.config.datasource.DynamicDataSource;
import com.gm.demo.nacos.server.common.config.datasource.DynamicDataSourceHolder;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
 
/**
 * @author Timi
 * @Date 2020/8/20 16:12
 */
@Configuration // 该注解类似于spring配置文件
@MapperScan("com.gm.demo.nacos.server.**.mapper*")
public class MyBatisPlusConfig {
    /**
     * 配置数据源
     * @return
     */
    @Bean(name = "master")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.master")
    public DataSource master() {
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "slave")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.slave")
    public DataSource slave() {
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dataSource(@Qualifier("master") DataSource master,
                                        @Qualifier("slave") DataSource slave) {
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DynamicDataSourceHolder.DB_MASTER, master);
        targetDataSource.put(DynamicDataSourceHolder.DB_SLAVE, slave);
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSource);
        return dataSource;
    }
 
 
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource)
            throws Exception {
        //配置mybatis,对应mybatis-config.xml
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        //懒加载
        LazyConnectionDataSourceProxy p=new LazyConnectionDataSourceProxy();
        p.setTargetDataSource(dynamicDataSource);
        sqlSessionFactory.setDataSource(p);
        //需要mapper文件时加入扫描，sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*/*Mapper.xml"));
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setUseGeneratedKeys(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
        //加入上面的两个拦截器
        Interceptor interceptor[]={paginationInterceptor()};
        sqlSessionFactory.setPlugins(interceptor);
        return sqlSessionFactory.getObject();
    }
 
    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }
 
}