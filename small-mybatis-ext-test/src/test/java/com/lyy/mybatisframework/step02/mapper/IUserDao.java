package com.lyy.mybatisframework.step02.mapper;

public interface IUserDao {

    String queryUserName(String username);

    Integer queryUserAge(String age);

}