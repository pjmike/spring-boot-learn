package com.pjmike.mybatis.service;

import com.pjmike.mybatis.dao.UserMapper;
import com.pjmike.mybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pjmike
 * @create 2018-09-09 14:53
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public int insertUser(User user) {
        return userMapper.insertSelective(user);
    }
}
