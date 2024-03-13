package com.lyy.mybatisframework.executor;

import com.lyy.mybatisframework.transaction.Transaction;
import com.lyy.mybatisframework.cache.CacheKey;
import com.lyy.mybatisframework.mapping.BoundSql;
import com.lyy.mybatisframework.mapping.MappedStatement;
import com.lyy.mybatisframework.session.ResultHandler;
import com.lyy.mybatisframework.session.RowBounds;

import java.sql.SQLException;
import java.util.List;

/**
 * @description: SQL执行器
 * @author：liuyuyan
 * @date: 2023/5/26
 */
public interface Executor {

    ResultHandler NO_RESULT_HANDLER = null;

    <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, CacheKey key, BoundSql boundSql) throws SQLException;

    <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler) throws SQLException;

    int update(MappedStatement ms, Object parameter) throws SQLException;

    Transaction getTransaction();

    void commit(boolean required) throws SQLException;

    void rollback(boolean required) throws SQLException;

    void close(boolean forceRollback);

    // 创建缓存 Key
    CacheKey createCacheKey(MappedStatement ms, Object parameterObject, RowBounds rowBounds, BoundSql boundSql);

    void setExecutorWrapper(Executor cachingExecutor);

    // 清理Session缓存
    void clearLocalCache();
}
