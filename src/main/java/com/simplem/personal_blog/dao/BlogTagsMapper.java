package com.simplem.personal_blog.dao;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogTagsMapper {

    List<Long> selectByBlogId(Long id);

    int insertBlogTag(Long blogId,Long tagId);

    void delete(Long id);

}