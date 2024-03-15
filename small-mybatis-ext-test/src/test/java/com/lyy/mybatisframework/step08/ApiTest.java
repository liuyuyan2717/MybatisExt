package com.lyy.mybatisframework.step08;

import com.alibaba.fastjson.JSON;
import com.lyy.mybatisframework.io.Resources;
import com.lyy.mybatisframework.session.SqlSession;
import com.lyy.mybatisframework.session.SqlSessionFactory;
import com.lyy.mybatisframework.session.SqlSessionFactoryBuilder;
import com.lyy.mybatisframework.step06.mapper.IUserDao;
import com.lyy.mybatisframework.step06.po.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_SqlSessionFactory() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 3. 测试验证
//        User user = userDao.queryUserInfoByIdAndUsername(1L, "liuyuyan");
//        User user = userDao.queryUserInfoByUsername("liuyuyan");
        User user = userDao.queryUserInfoById(1L);
        logger.info("测试结果：{}", JSON.toJSONString(user));
    }

}
