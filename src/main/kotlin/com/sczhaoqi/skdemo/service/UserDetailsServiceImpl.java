package com.sczhaoqi.skdemo.service;

import com.sczhaoqi.skdemo.dao.UserDao;
import com.sczhaoqi.skdemo.pojo.dto.UserAccountDto;
import com.sczhaoqi.skdemo.pojo.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author sczhaoqi
 * @date 2019/5/4 2:14
 */
@Service
public class UserDetailsServiceImpl
        implements UserDetailsService, UserService
{
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException
    {
        User user = userDao.findUserByUsername(username);
        if (user == null) {
            throw new BadCredentialsException("账号不存在");
        }

        return new UserAccountDto(user);
    }

    @Override
    public User findUserByUsername(String username)
            throws UsernameNotFoundException
    {
        User user = userDao.findUserByUsername(username);
        if (user == null) {
            throw new BadCredentialsException("账号不存在");
        }
        return user;
    }
}
