package com.lyy.mybatisframework.scripting.xmltags;

import com.lyy.mybatisframework.scripting.defaults.DefaultParameterHandler;
import com.lyy.mybatisframework.scripting.defaults.RawSqlSource;
import com.lyy.mybatisframework.executor.parameter.ParameterHandler;
import com.lyy.mybatisframework.mapping.BoundSql;
import com.lyy.mybatisframework.mapping.MappedStatement;
import com.lyy.mybatisframework.mapping.SqlSource;
import com.lyy.mybatisframework.scripting.LanguageDriver;
import com.lyy.mybatisframework.session.Configuration;
import org.dom4j.Element;

/**
 * @description: XML 的 解析sql语言驱动器
 * @author：liuyuyan
 * @date: 2023/5/28
 */
public class XMLLanguageDriver implements LanguageDriver {

    /**
     * 利用XMLScriptBuilder 解析XML配置的原生SQL
     *
     * @param configuration 全局配置
     * @param element select|update|insert|delete标签的dom对象
     * @param parameterType Integer, String, User..... xml中配置的参数类型的class，一般都是null
     * @return SqlSource
     */
    @Override
    public SqlSource createSqlSource(Configuration configuration, Element element, Class<?> parameterType) {
        // 用XML脚本构建器解析
        XMLScriptBuilder builder = new XMLScriptBuilder(configuration, element, parameterType);
        return builder.parseScriptNode();
    }

    /**
     * step-12 新增方法，用于处理注解配置 SQL 语句
     */
    @Override
    public SqlSource createSqlSource(Configuration configuration, String sqlScript, Class<?> parameterType) {
        // 暂时不解析动态 SQL
        return new RawSqlSource(configuration, sqlScript, parameterType);
    }


    @Override
    public ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        return new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
    }
}
