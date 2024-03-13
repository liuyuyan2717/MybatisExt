package com.lyy.mybatisframework.step06.mapper;


import com.lyy.mybatisframework.step06.po.User;

public interface IUserDao {

    User queryUserInfoById(Long uId);

}
