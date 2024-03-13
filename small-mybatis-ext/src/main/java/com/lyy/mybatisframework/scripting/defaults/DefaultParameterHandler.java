package com.lyy.mybatisframework.scripting.defaults;

import com.alibaba.fastjson.JSON;
import com.lyy.mybatisframework.executor.parameter.ParameterHandler;
import com.lyy.mybatisframework.mapping.BoundSql;
import com.lyy.mybatisframework.mapping.MappedStatement;
import com.lyy.mybatisframework.mapping.ParameterMapping;
import com.lyy.mybatisframework.reflection.MetaObject;
import com.lyy.mybatisframework.session.Configuration;
import com.lyy.mybatisframework.type.JdbcType;
import com.lyy.mybatisframework.type.TypeHandler;
import com.lyy.mybatisframework.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @description: 默认参数处理器
 * @author：liuyuyan
 * @date: 2023/5/29
 */
public class DefaultParameterHandler implements ParameterHandler {

    private Logger logger = LoggerFactory.getLogger(DefaultParameterHandler.class);
    private final TypeHandlerRegistry typeHandlerRegistry;
    private Configuration configuration;
    private final Object parameterObject;
    private BoundSql boundSql;
//    private final MappedStatement mappedStatement;


    public DefaultParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        this.configuration = mappedStatement.getConfiguration();
        this.typeHandlerRegistry = mappedStatement.getConfiguration().getTypeHandlerRegistry();
        this.parameterObject = parameterObject;
        this.boundSql = boundSql;
//        this.mappedStatement = mappedStatement;
    }

    @Override
    public Object getParameterObject() {
        return parameterObject;
    }

    @Override
    public void setParameters(PreparedStatement ps) throws SQLException {
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (null != parameterMappings) {
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                String propertyName = parameterMapping.getProperty();
                Object value;
                if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                    value = parameterObject;
                } else {
                    // 通过 MetaObject.getValue 反射取得值设进去
                    MetaObject metaObject = configuration.newMetaObject(parameterObject);
                    value = metaObject.getValue(propertyName);
                }
                JdbcType jdbcType = parameterMapping.getJdbcType();

                // 设置参数
                logger.info("根据每个ParameterMapping中的TypeHandler设置对应的参数信息 value：{}", JSON.toJSONString(value));
                TypeHandler typeHandler = parameterMapping.getTypeHandler();
                typeHandler.setParameter(ps, i + 1, value, jdbcType);
            }
        }
    }
}
