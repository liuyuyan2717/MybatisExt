package com.lyy.mybatisframework.datasource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @description 数据源工厂
 * @author liuyuyan
 * @date 2023/05/25
 */
public interface DataSourceFactory {

    void setProperties(Properties props);

    DataSource getDataSource();

}
