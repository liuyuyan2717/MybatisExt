package com.lyy.mybatisframework.step09.mapper;


import com.lyy.mybatisframework.step09.po.User;

public interface IUserDao {

    User queryUserInfoById(Long uId);

    User queryUserInfoByIdAndUsername(Long uId, String username);

    User queryUserInfoByUsername(String username);

    User queryUserInfo(User req);

}
