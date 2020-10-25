package com.simplem.personal_blog.service;

import com.simplem.personal_blog.model.Blog;
import com.simplem.personal_blog.vo.FirstPageBlog;
import com.simplem.personal_blog.vo.indexBlog;

import java.util.List;

/**
 * ClassName: BlogService
 * Package: com.simplem.personal_blog.service
 * Description：
 * Author: simpleM
 * Date: 2020/10/21 12:08
 */
public interface BlogService {

    List<FirstPageBlog> getIndexBlog();  //主页博客展示

    List<Blog> getAllBlog();

    List<Blog> searchAllBlog(Blog blog);

    List<indexBlog> getNewBlogs();

    List<indexBlog> findRecommendBlogs();

    List<FirstPageBlog> findAllByTypeId(Long typeId);//分类页面展示的博客

}
