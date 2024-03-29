package com.lyy.mybatisframework.executor.result;

import com.lyy.mybatisframework.reflection.factory.ObjectFactory;
import com.lyy.mybatisframework.session.ResultContext;
import com.lyy.mybatisframework.session.ResultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 默认结果处理器
 * @author：liuyuyan
 * @date: 2023/5/29
 */
public class DefaultResultHandler implements ResultHandler {
    private final List<Object> list;

    public DefaultResultHandler() {
        this.list = new ArrayList<>();
    }
    /**
     * 通过 ObjectFactory 反射工具类，产生特定的 List
     */
    @SuppressWarnings("unchecked")
    public DefaultResultHandler(ObjectFactory objectFactory) {
        this.list = objectFactory.create(List.class);
    }

    public List<Object> getResultList() {
        return list;
    }

    @Override
    public void handleResult(ResultContext context) {
        list.add(context.getResultObject());
    }
}

