package com.lyy.mybatisframework.reflection.wrapper;

import com.lyy.mybatisframework.reflection.MetaObject;

/**
 * @description: 默认对象包装工厂 , 不提供任何实现
 * @author：liuyuyan
 * @date: 2023/5/27
 */
public class DefaultObjectWrapperFactory implements ObjectWrapperFactory{
    @Override
    public boolean hasWrapperFor(Object object) {
        return false;
    }

    @Override
    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
        throw new RuntimeException("The DefaultObjectWrapperFactory should never be called to provide an ObjectWrapper.");
    }
}
