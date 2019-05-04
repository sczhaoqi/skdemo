package com.sczhaoqi.skdemo.dao;

import com.sczhaoqi.skdemo.pojo.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sczhaoqi
 * @date 2019/5/4 2:16
 */
@Repository
public interface UserDao
        extends JpaRepository<User, Long>
{
    /**
     * @param username 用户名
     * @return
     */
    User findUserByUsername(String username);
}
