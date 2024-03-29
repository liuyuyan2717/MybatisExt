package com.lyy.mybatisframework.executor.resultset;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @description: 结果集处理器
 * @author：liuyuyan
 * @date: 2023/5/26
 */
public interface ResultSetHandler {

    <E> List<E> handleResultSets(Statement stmt) throws SQLException;
}
