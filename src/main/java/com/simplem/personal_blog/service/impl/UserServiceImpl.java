package com.simplem.personal_blog.service.impl;

import com.simplem.personal_blog.dao.UserMapper;
import com.simplem.personal_blog.model.User;
import com.simplem.personal_blog.service.UserService;
import com.simplem.personal_blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: UserServiceImpl
 * Package: com.simplem.personal_blog.service.impl
 * Description：
 * Author: simpleM
 * Date: 2020/10/20 17:57
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        password = MD5Utils.code(password);
        return userMapper.queryByUsernameAndPassword(username,password);
    }
}
