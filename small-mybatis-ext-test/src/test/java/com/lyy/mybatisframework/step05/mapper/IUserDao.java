package com.lyy.mybatisframework.step05.mapper;

import com.lyy.mybatisframework.step05.po.User;

public interface IUserDao {

    User queryUserInfoById(Long uId);
}