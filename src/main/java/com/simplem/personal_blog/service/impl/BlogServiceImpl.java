package com.simplem.personal_blog.service.impl;

import com.simplem.personal_blog.dao.BlogMapper;
import com.simplem.personal_blog.model.Blog;
import com.simplem.personal_blog.service.BlogService;
import com.simplem.personal_blog.vo.FirstPageBlog;
import com.simplem.personal_blog.vo.indexBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: BlogServiceImpl
 * Package: com.simplem.personal_blog.service.impl
 * Description：
 * Author: simpleM
 * Date: 2020/10/21 12:08
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public List<Blog> searchAllBlog(Blog blog) {
        return blogMapper.searchAllBlog(blog);
    }

    @Override
    public List<FirstPageBlog> getIndexBlog() {
        return blogMapper.findAll();
    }

    @Override
    public List<Blog> getAllBlog() {
        return blogMapper.getAllBlog();
    }

    @Override
    public List<indexBlog> findRecommendBlogs() {
        return blogMapper.findRecommendBlogs();
    }

    @Override
    public List<indexBlog> getNewBlogs() {
        return blogMapper.getNewBlogs();
    }

    @Override
    public List<FirstPageBlog> findAllByTypeId(Long typeId) {
        return blogMapper.findAllByTypeId(typeId);
    }
}
