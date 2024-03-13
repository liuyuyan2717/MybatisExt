package com.lyy.mybatisframework.datasource.pooled;

import com.lyy.mybatisframework.datasource.unpooled.UnpooledDataSourceFactory;

/**
 * @author liuyuyan
 * @description 有连接池的数据源工厂
 * @date 2023/05/26
 */
public class PooledDataSourceFactory extends UnpooledDataSourceFactory {

    public PooledDataSourceFactory() {
        this.dataSource = new PooledDataSource();
    }

}
