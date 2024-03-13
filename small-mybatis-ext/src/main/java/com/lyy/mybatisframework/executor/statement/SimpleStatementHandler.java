package com.lyy.mybatisframework.executor.statement;

import com.lyy.mybatisframework.executor.Executor;
import com.lyy.mybatisframework.mapping.BoundSql;
import com.lyy.mybatisframework.mapping.MappedStatement;
import com.lyy.mybatisframework.session.ResultHandler;
import com.lyy.mybatisframework.session.RowBounds;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @description: 简单语句处理器（STATEMENT） 不带 ? 参数的
 * @author：liuyuyan
 * @date: 2023/5/26
 */
public class SimpleStatementHandler extends BaseStatementHandler{

    public SimpleStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameterObject, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        super(executor, mappedStatement, parameterObject, rowBounds, resultHandler, boundSql);
    }

    /**
     * 不带？参数，可直接运行的SQL语句， 直接返回
     * @param connection
     * @return
     * @throws SQLException
     */
    @Override
    protected Statement instantiateStatement(Connection connection) throws SQLException {
        return connection.createStatement();
    }

    @Override
    public void parameterize(Statement statement) throws SQLException {
        // N/A
    }

    /**
     * 这里ResultHandler 的作用？
     * @param statement sql, sql参数， sql待处理结果
     * @param resultHandler  未知
     * @return 处理后的SQL结果List
     */
    @Override
    public <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException {
        String sql = boundSql.getSql();
        statement.execute(sql);
        return resultSetHandler.handleResultSets(statement);
    }

    @Override
    public int update(Statement stmt) throws SQLException {
        String sql = boundSql.getSql();
        stmt.execute(sql);
        return stmt.getUpdateCount();
    }
}
