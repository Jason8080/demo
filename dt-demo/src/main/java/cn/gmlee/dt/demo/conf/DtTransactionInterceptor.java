package cn.gmlee.dt.demo.conf;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * 事务拦截器.
 */
public class DtTransactionInterceptor extends TransactionInterceptor {

    @Override
    protected TransactionInfo createTransactionIfNecessary(PlatformTransactionManager tm, TransactionAttribute txAttr, String s) {
        return super.createTransactionIfNecessary(tm, txAttr, s);
    }

    @Override
    protected void commitTransactionAfterReturning(TransactionInfo txInfo) {
//        super.commitTransactionAfterReturning(txInfo);
    }

    @Override
    protected void completeTransactionAfterThrowing(TransactionInfo txInfo, Throwable ex) {
        super.completeTransactionAfterThrowing(txInfo, ex);
    }
}
