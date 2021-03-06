package com.simplem.personal_blog.dao;


import com.simplem.personal_blog.model.Blog;
import com.simplem.personal_blog.vo.BlogQuery;
import com.simplem.personal_blog.vo.FirstPageBlog;
import com.simplem.personal_blog.vo.IndexBlog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper {

    List<Blog> getAllBlog();//获取数据，后台展示博客

    List<Blog> searchAllBlog(Blog blog);  //后台根据标题、分类、推荐搜索博客

    List<FirstPageBlog> findAll();

    List<IndexBlog> getNewBlogs();

    List<IndexBlog> findRecommendBlogs();   //首页展示的博客

    List<FirstPageBlog> findAllByTypeId(Long typeId);//分类页面展示的博客

    List<FirstPageBlog> findAllByTagId(Long tagId);//分类页面展示的博客

    Blog selectBlogById(Long id);

    void updateViews(Long id);

    Blog getBlogById(Long id);

    int save(Blog blog);

    Blog getBlog(Long id);

    int update(Blog blog);

    int delete(Long id);

    List<Blog> getAllBlogBySearch(BlogQuery blogQuery);

    List<FirstPageBlog> searchBlog(BlogQuery blogQuery);

    List<String> findGroupYear();  //查询所有年份，返回一个集合

    List<Blog> findByYear(@Param("year") String year);  //按年份查询博客

}