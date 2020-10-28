package com.simplem.personal_blog.controller;

import com.simplem.personal_blog.model.Blog;
import com.simplem.personal_blog.service.BlogService;
import com.simplem.personal_blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ClassName: BlogShowController
 * Package: com.simplem.personal_blog.controller
 * Description：
 * Author: simpleM
 * Date: 2020/10/25 18:17
 */
@Controller
public class BlogShowController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @GetMapping("/blog/{id}")
    public String showBlog(@PathVariable Long id, Model model){
        Blog blog = blogService.selectBlogById(id);
        model.addAttribute("blog",blog);
        return "blog";
    }
}
