package com.lyy.mybatisframework.session.defaults;

import com.lyy.mybatisframework.executor.Executor;
import com.lyy.mybatisframework.mapping.Environment;
import com.lyy.mybatisframework.session.Configuration;
import com.lyy.mybatisframework.session.SqlSession;
import com.lyy.mybatisframework.session.SqlSessionFactory;
import com.lyy.mybatisframework.session.TransactionIsolationLevel;
import com.lyy.mybatisframework.transaction.Transaction;
import com.lyy.mybatisframework.transaction.TransactionFactory;

/**
 * @description: 默认的 DefaultSqlSessionFactory
 * @author：liuyuyan
 * @date: 2023/5/23
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    /*
    以前通过手动注册的方式
    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
     */
    /**
     * 现在通过解析<mappers></mappers>配置的方式自动注册
     */
    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        final Environment environment = configuration.getEnvironment();
        /* 从xml读取的，并反射实例化后赋值给Environment */
        TransactionFactory transactionFactory = environment.getTransactionFactory();
        /* 设置事务的隔离级别读已提交 和 事务自动提交 */
        Transaction tx = transactionFactory.newTransaction(configuration.getEnvironment().getDataSource(), TransactionIsolationLevel.READ_COMMITTED, false);
        // 创建执行器
        final Executor executor = configuration.newExecutor(tx);
        return new DefaultSqlSession(configuration, executor);
    }

}
