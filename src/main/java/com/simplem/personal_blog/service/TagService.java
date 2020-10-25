package com.simplem.personal_blog.service;

import com.simplem.personal_blog.model.Tag;

import java.util.List;

/**
 * ClassName: TagService
 * Package: com.simplem.personal_blog.service
 * Description：
 * Author: simpleM
 * Date: 2020/10/22 20:04
 */
public interface TagService {
    List<Tag> findAllTags();

    Tag findTagById(Long id);

    int addTay(Tag tag);

    Tag selectByName(String name);

    int updateTag(Tag tag);

    void deleteType(Long id);

    List<Tag> getIndexTag();
}
