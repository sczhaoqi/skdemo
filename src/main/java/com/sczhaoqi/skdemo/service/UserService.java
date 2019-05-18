package com.sczhaoqi.skdemo.service;

import com.sczhaoqi.skdemo.pojo.po.User;

/**
 * @author sczhaoqi
 * @date 2019/5/4 23:30
 */
public interface UserService
{
    User findUserByUsername(String username);
}
