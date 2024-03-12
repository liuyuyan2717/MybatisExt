package com.lyy.mybatisframework.plugin.impl.slowsql;

import com.lyy.mybatisframework.executor.Executor;
import com.lyy.mybatisframework.mapping.MappedStatement;
import com.lyy.mybatisframework.plugin.Interceptor;
import com.lyy.mybatisframework.plugin.Intercepts;
import com.lyy.mybatisframework.plugin.Invocation;
import com.lyy.mybatisframework.plugin.Signature;
import com.lyy.mybatisframework.session.Configuration;
import com.lyy.mybatisframework.session.ResultHandler;
import com.lyy.mybatisframework.session.RowBounds;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @description: 慢查询sql
 * @author：liuyuyan
 * @date: 2023/6/3
 */
@Intercepts({@Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class SlowSqlLoggerPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return invocation.proceed();
        }finally {
            Object[] args = invocation.getArgs();
            MappedStatement ms = (MappedStatement) args[0];
            Object objectParameter = args[1];
            Configuration configuration = ms.getConfiguration();
            String filePath = configuration.getSlowSqlLogPath();
            int timeLimit = configuration.getSlowSqlTimeLimit();

            long end = System.currentTimeMillis();
            long executorResult = end - start;
            if (executorResult > timeLimit) {
                try (FileWriter writer = new FileWriter(filePath, true)) {
                    writer.write("一条慢sql: " + ms.getBoundSql(objectParameter).getSql() + " 执行时间: "+ executorResult +"ms\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
