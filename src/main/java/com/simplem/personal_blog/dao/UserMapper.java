package com.simplem.personal_blog.dao;

import com.simplem.personal_blog.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    User queryByUsernameAndPassword(String username,String password);
}