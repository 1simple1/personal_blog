package com.simplem.personal_blog.dao;


import com.simplem.personal_blog.model.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeMapper {

    List<Type> selectAllTypes();

    Type selectById(Long id);

    int insertType(Type type);

    Type selectByName(String name);

    int updateTypeById(Type type);

    void deleteTypeById(Long id);
}