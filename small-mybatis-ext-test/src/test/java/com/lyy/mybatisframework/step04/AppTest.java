package com.lyy.mybatisframework.step04;


import com.alibaba.fastjson.JSON;
import com.lyy.mybatisframework.io.Resources;
import com.lyy.mybatisframework.session.SqlSession;
import com.lyy.mybatisframework.session.SqlSessionFactory;
import com.lyy.mybatisframework.session.SqlSessionFactoryBuilder;
import com.lyy.mybatisframework.step04.mapper.IUserDao;
import com.lyy.mybatisframework.step04.po.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;

/**
 * Unit test for simple App.
 */

public class AppTest {
    private Logger logger = LoggerFactory.getLogger(AppTest.class);

    @Test
    public void test_MapperProxy() throws IOException {
        // 1. 注册 Mapper
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");

        // 2. 解析xml 获取SqlSession 工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        // 3. 从 sqlSessionFactory 获取 SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 获取映射器对象
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);

        // 4. 测试验证
        User user = mapper.queryUserInfoById(1L);
        logger.info("测试结果：{}", JSON.toJSONString(user));
    }
}