package com.lyy.mybatisframework.scripting.xmltags;

/**
 * @description: 抽象SQL节点
 *              一个Sql语句 具体子类包括静态文本节点 if节点 where节点 set节点等
 * @author：liuyuyan
 * @date: 2023/5/28
 */
public interface SqlNode {

    boolean apply(DynamicContext context);
}
