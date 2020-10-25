package com.simplem.personal_blog.dao;

import com.simplem.personal_blog.model.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagMapper {
    List<Tag> selectAllTags();

    Tag selectById(Long id);

    int insertTag(Tag tag);

    Tag selectByName(String name);

    int updateTag(Tag tag);

    void deleteTypeById(Long id);

    List<Tag> getIndexTag();
}