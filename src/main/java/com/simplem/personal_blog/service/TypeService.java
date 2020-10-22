package com.simplem.personal_blog.service;

import com.simplem.personal_blog.model.Type;

import java.util.List;

/**
 * ClassName: TypeService
 * Package: com.simplem.personal_blog.service
 * Description：
 * Author: simpleM
 * Date: 2020/10/22 10:47
 */
public interface TypeService {

    List<Type> getAllType();

    Type selectByid(Long id);

    int insertType(Type type);

    Type selectByName(String name);

    int updateType(Type type);

    void deleteType(Long id);
}
