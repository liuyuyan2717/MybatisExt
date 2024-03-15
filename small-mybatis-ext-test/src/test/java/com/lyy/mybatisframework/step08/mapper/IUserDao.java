package com.lyy.mybatisframework.step08.mapper;


import com.lyy.mybatisframework.step06.po.User;

public interface IUserDao {

    User queryUserInfoById(Long uId);
    User queryUserInfoByIdAndUsername(Long uId, String username);

    User queryUserInfoByUsername(String username);

}
