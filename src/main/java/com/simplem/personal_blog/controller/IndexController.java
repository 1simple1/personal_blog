package com.simplem.personal_blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simplem.personal_blog.model.Tag;
import com.simplem.personal_blog.model.Type;
import com.simplem.personal_blog.service.BlogService;
import com.simplem.personal_blog.service.TagService;
import com.simplem.personal_blog.service.TypeService;
import com.simplem.personal_blog.vo.FirstPageBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * ClassName: IndexController
 * Package: com.simplem.personal_blog.controller
 * Description：
 * Author: simpleM
 * Date: 2020/10/20 22:01
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "1") int pageNum,
//                        @RequestParam(defaultValue = "5") int pageSize,
                        Model model){
//        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum, 5);
        List<FirstPageBlog> firstPageBlogs = blogService.getIndexBlog();
        PageInfo<FirstPageBlog> blogPageInfo = new PageInfo<>(firstPageBlogs);
        model.addAttribute("blogPageInfo",blogPageInfo);
        model.addAttribute("types",typeService.getIndexType());
        model.addAttribute("tags",tagService.getIndexTag());
        model.addAttribute("recommendBlogs",blogService.findRecommendBlogs());
        return "index";
    }

    @GetMapping("/footer/newblog")
    public String footerNewBlogs(@RequestParam(defaultValue = "1") int pageNum,
                                 @RequestParam(defaultValue = "5") int pageSize,
                                 Model model){
        model.addAttribute("newBlogs",blogService.getNewBlogs());
        return "fragments :: newBlogList";
    }
}
