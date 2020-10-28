package com.simplem.personal_blog.service.impl;

import com.simplem.personal_blog.dao.BlogMapper;
import com.simplem.personal_blog.dao.BlogTagsMapper;
import com.simplem.personal_blog.dao.TagMapper;

import com.simplem.personal_blog.model.Blog;
import com.simplem.personal_blog.model.Tag;
import com.simplem.personal_blog.service.BlogService;
import com.simplem.personal_blog.util.MarkdownUtils;
import com.simplem.personal_blog.util.MyBeanUtils;
import com.simplem.personal_blog.vo.BlogQuery;
import com.simplem.personal_blog.vo.FirstPageBlog;
import com.simplem.personal_blog.vo.IndexBlog;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    private BlogTagsMapper blogTagsMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private BlogTagsMapper blogTagMapper;

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
    public List<IndexBlog> findRecommendBlogs() {
        return blogMapper.findRecommendBlogs();
    }

    @Override
    public List<IndexBlog> getNewBlogs() {
        return blogMapper.getNewBlogs();
    }

    @Override
    public List<FirstPageBlog> findAllByTypeId(Long typeId) {
        return blogMapper.findAllByTypeId(typeId);
    }

    @Transactional //声明式事务管理
    @Override
    public Blog selectBlogById(Long id) {
        Blog blog = blogMapper.selectBlogById(id);
        //将博客的内容转换成HTML格式再返回
        Blog b = new Blog(); //创建一个新的blog对象，防止直接将content转换成HTML格式
        BeanUtils.copyProperties(blog,b);
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(b.getContent()));
        blogMapper.updateViews(id);
        return b;
    }

    @Override
    public List<FirstPageBlog> findAllByTagId(Long tagId) {
        return blogMapper.findAllByTagId(tagId);
    }

//    @Transactional //声明式事务管理
    @Override
    public Blog getBlogById(Long id) {
        Blog blog = blogMapper.getBlogById(id);
        List<Long> tagIdList = blogTagsMapper.selectByBlogId(id);
        List<Tag> tags = tagMapper.findAllByIds(tagIdList);
        blog.setTagIds(tagsToIds(tags));
        return blog;
    }

    // 将List<Tag>转换成字符串格式(1,2,3)
    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return null;
        }
    }

    @Transactional //声明式事务管理
    @Override
    public int save(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(1);
        int save = blogMapper.save(blog);
        handlerBlogTag(blog.getId(),blog.getTags());
        return save;
    }

    private void handlerBlogTag(Long blogId, List<Tag> tags) {
        for (Tag tag : tags) {
            blogTagMapper.insertBlogTag(blogId, tag.getId());
        }
    }

    @Transactional //声明式事务管理
    @Override
    public int update(Blog blog) {
        Blog b = blogMapper.getBlog(blog.getId());//b是旧数据
        BeanUtils.copyProperties(blog, b, MyBeanUtils.getNullPropertyNames(blog));//将blog不为空的属性值存到b中(blog是新数据)
        b.setUpdateTime(new Date());//设置更新时间
        int update =  blogMapper.update(b);
        blogTagMapper.delete(blog.getId());//先删除所有与博客对应标签id
        handlerBlogTag(blog.getId(), blog.getTags());//再插入新的
        return update;
    }

    @Override
    public int delete(Long id) {
        blogTagMapper.delete(id);
        return blogMapper.delete(id);
    }

    @Override
    public List<Blog> getAllBlogBySearch(BlogQuery blogQuery) {
        return blogMapper.getAllBlogBySearch(blogQuery);
    }
}
