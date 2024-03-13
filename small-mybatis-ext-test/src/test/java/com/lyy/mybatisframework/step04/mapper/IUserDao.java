package com.lyy.mybatisframework.step04.mapper;

import com.lyy.mybatisframework.step04.po.User;

public interface IUserDao {

    User queryUserInfoById(Long uId);
}