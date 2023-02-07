package cn.gmlee.dt.demo.conf;

import cn.hll.tools.ds.config.dynamic.DynamicDatasourceAutoConfiguration;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Role;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * 事务管理配置类.
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter({DynamicDatasourceAutoConfiguration.class, TransactionAutoConfiguration.class})
public class DtTransactionManagementAutoConfiguration {

    @Primary
    @Bean("DtTransactionInterceptor")
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public TransactionInterceptor transactionInterceptor(TransactionManager transactionManager) {
        TransactionInterceptor interceptor = new DtTransactionInterceptor();
        interceptor.setTransactionAttributeSource(new AnnotationTransactionAttributeSource());
        interceptor.setTransactionManager(transactionManager);
        return interceptor;
    }
}
