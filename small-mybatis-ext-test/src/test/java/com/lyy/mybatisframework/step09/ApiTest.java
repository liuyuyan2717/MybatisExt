package com.lyy.mybatisframework.step09;

import com.alibaba.fastjson.JSON;
import com.lyy.mybatisframework.io.Resources;
import com.lyy.mybatisframework.session.SqlSession;
import com.lyy.mybatisframework.session.SqlSessionFactory;
import com.lyy.mybatisframework.session.SqlSessionFactoryBuilder;
import com.lyy.mybatisframework.step09.mapper.IUserDao;
import com.lyy.mybatisframework.step09.po.User;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ApiTest {


    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    private SqlSession sqlSession;

    @Before
    public void init() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void test_queryUserInfoById() {
        // 1. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 2. 测试验证：基本参数
//        User user = userDao.queryUserInfoById(1L);
        User user = userDao.queryUserInfoByIdAndUsername(1L,"liuyuyan");
        logger.info("测试结果：{}", JSON.toJSONString(user));
    }

    @Test
    public void test_queryUserInfo() {
        // 1. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 2. 测试验证：对象参数
        User user = userDao.queryUserInfo(new User(1L, "10001"));
        logger.info("测试结果：{}", JSON.toJSONString(user));
    }

}
