package com.simplem.personal_blog.service.impl;

import com.simplem.personal_blog.dao.TagMapper;
import com.simplem.personal_blog.model.Tag;
import com.simplem.personal_blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: TagServiceImpl
 * Package: com.simplem.personal_blog.service.impl
 * Description：
 * Author: simpleM
 * Date: 2020/10/22 20:04
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> findAllTags() {
        return tagMapper.selectAllTags();
    }

    @Override
    public Tag selectByName(String name) {
        return tagMapper.selectByName(name);
    }

    @Override
    public int addTay(Tag tag) {
        return tagMapper.insertTag(tag);
    }

    @Override
    public int updateTag(Tag tag) {
        return tagMapper.updateTag(tag);
    }

    @Override
    public Tag findTagById(Long id) {
        return tagMapper.selectById(id);
    }

    @Override
    public void deleteType(Long id) {
        tagMapper.deleteTypeById(id);
    }
}
