package com.lyy.mybatisframework.executor;

import com.lyy.mybatisframework.executor.statement.StatementHandler;
import com.lyy.mybatisframework.transaction.Transaction;
import com.lyy.mybatisframework.mapping.BoundSql;
import com.lyy.mybatisframework.mapping.MappedStatement;
import com.lyy.mybatisframework.session.Configuration;
import com.lyy.mybatisframework.session.ResultHandler;
import com.lyy.mybatisframework.session.RowBounds;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @description: 简单执行器，每次执行sql语句都需要重新编译
 * @author：liuyuyan
 * @date: 2023/5/26
 */
public class SimpleExecutor extends BaseExecutor {

    public SimpleExecutor(Configuration config, Transaction tx) {
        super(config, tx);
    }

    @Override
    protected <E> List<E> doQuery(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        try {
            // 1 在这一步完成Statement的创建，并且相应的参数处理器和结果处理器也在这里完成初始化
            StatementHandler handler = configuration.newStatementHandler(this, ms, parameter, rowBounds, resultHandler, boundSql);
            // 2 建立连接
            Connection connection = transaction.getConnection();
            // 3 获取statement(sql, sql参数， sql执行结果)
            Statement stmt = handler.prepare(connection);
            // 4 处理sql的参数
            handler.parameterize(stmt);
            // 5 执行sql, 处理结果
            return handler.query(stmt, resultHandler);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    protected int doUpdate(MappedStatement ms, Object parameter) throws SQLException {
        Statement stmt = null;
        try {
            Configuration configuration = ms.getConfiguration();
            // 新建一个 StatementHandler
            StatementHandler handler = configuration.newStatementHandler(this, ms, parameter, RowBounds.DEFAULT, null, null);
            // 准备语句
            stmt = prepareStatement(handler);
            // StatementHandler.update
            return handler.update(stmt);
        } finally {
            closeStatement(stmt);
        }
    }

    private Statement prepareStatement(StatementHandler handler) throws SQLException {
        Statement stmt;
        Connection connection = transaction.getConnection();
        // 准备语句
        stmt = handler.prepare(connection);
        handler.parameterize(stmt);
        return stmt;
    }

}
