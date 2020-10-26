package com.simplem.personal_blog.service;

import com.simplem.personal_blog.model.User;

/**
 * ClassName: UserService
 * Package: com.simplem.personal_blog.service
 * Description：
 * Author: simpleM
 * Date: 2020/10/20 17:56
 */
public interface UserService {
    //用户登录
    User login(String username,String password);

    User selectUserById(Long userId);
}
