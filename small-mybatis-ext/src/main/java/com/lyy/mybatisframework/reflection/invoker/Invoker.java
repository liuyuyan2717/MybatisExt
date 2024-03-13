package com.lyy.mybatisframework.reflection.invoker;

/**
 * @description: 反射调用者
 * @author：liuyuyan
 * @date: 2023/5/26
 */
public interface Invoker {

    Object invoke(Object target, Object[] args) throws Exception;

    Class<?> getType();
}
