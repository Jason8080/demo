package com.gm.demo.nacos.server.common.config.datasource;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DruidConfig {

    @Value("${login-username}")
    private static String username;
    @Value("${login-password}")
    private static String password;

    @Autowired
    WallFilter wallFilter;

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource druidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        List<Filter> filters = new ArrayList();
        filters.add(this.wallFilter);
        dataSource.setProxyFilters(filters);
        return dataSource;
    }

    @Bean(
        name = {"wallConfig"}
    )
    WallConfig wallFilterConfig() {
        WallConfig wc = new WallConfig();
        wc.setMultiStatementAllow(true);
        return wc;
    }

    @Bean(
        name = {"wallFilter"}
    )
    @DependsOn({"wallConfig"})
    WallFilter wallFilter(WallConfig wallConfig) {
        WallFilter wfilter = new WallFilter();
        wfilter.setConfig(wallConfig);
        return wfilter;
    }

    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet(), new String[]{"/druid/*"});
        registrationBean.addInitParameter("allow", "");
        registrationBean.addInitParameter("deny", "");
        registrationBean.addInitParameter("loginUsername", username);
        registrationBean.addInitParameter("loginPassword", password);
        registrationBean.addInitParameter("resetEnable", "false");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean druidWebStatViewFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new WebStatFilter(), new ServletRegistrationBean[0]);
        registrationBean.addInitParameter("urlPatterns", "/*");
        registrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        return registrationBean;
    }
}
