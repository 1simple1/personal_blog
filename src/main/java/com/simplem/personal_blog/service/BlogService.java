package com.simplem.personal_blog.service;

import com.simplem.personal_blog.model.Blog;
import com.simplem.personal_blog.vo.BlogQuery;
import com.simplem.personal_blog.vo.FirstPageBlog;
import com.simplem.personal_blog.vo.IndexBlog;

import java.util.List;
import java.util.Map;

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

    List<IndexBlog> getNewBlogs();

    List<IndexBlog> findRecommendBlogs();

    List<FirstPageBlog> findAllByTypeId(Long typeId);//分类页面展示的博客

    List<FirstPageBlog> findAllByTagId(Long tagId);//分类页面展示的博客

    Blog selectBlogById(Long id);

    Blog getBlogById(Long id);

    int save(Blog blog);

    int update(Blog blog);

    int delete(Long id);

    List<Blog> getAllBlogBySearch(BlogQuery blogQuery);

    List<FirstPageBlog> searchBlog(BlogQuery blogQuery);

    Map<String,List<Blog>> archiveBlog();  //归档博客

    int countBlog();  //查询博客条数
}
