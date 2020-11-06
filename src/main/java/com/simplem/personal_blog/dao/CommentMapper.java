package com.simplem.personal_blog.dao;


import com.simplem.personal_blog.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    List<Comment> listCommentByBlogId(Long blogId);

    int saveComments(Comment comment);

    Comment getCommentByParentId(@Param("parentId") Long parentId);

}