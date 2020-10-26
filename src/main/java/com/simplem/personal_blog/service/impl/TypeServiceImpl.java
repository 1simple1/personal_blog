package com.simplem.personal_blog.service.impl;

import com.simplem.personal_blog.dao.TypeMapper;
import com.simplem.personal_blog.model.Type;
import com.simplem.personal_blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: TypeServiceImpl
 * Package: com.simplem.personal_blog.service.impl
 * Description：
 * Author: simpleM
 * Date: 2020/10/22 10:49
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<Type> getIndexType() {
        return typeMapper.getIndexType();
    }

    @Override
    public Type selectById(Long id) {
        return typeMapper.selectById(id);
    }

    @Override
    public void deleteType(Long id) {
        typeMapper.deleteTypeById(id);
    }

    @Override
    public int updateType(Type type) {
        return typeMapper.updateTypeById(type);
    }

    @Override
    public int insertType(Type type) {
        return typeMapper.insertType(type);
    }

    @Override
    public List<Type> getAllType() {
        return typeMapper.selectAllTypes();
    }

    @Override
    public Type selectByName(String name) {
        return typeMapper.selectByName(name);
    }
}
