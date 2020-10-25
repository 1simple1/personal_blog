package com.simplem.personal_blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simplem.personal_blog.model.Blog;
import com.simplem.personal_blog.model.Type;
import com.simplem.personal_blog.service.TagService;
import com.simplem.personal_blog.service.TypeService;
import com.simplem.personal_blog.service.impl.BlogServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * ClassName: BlogController
 * Package: com.simplem.personal_blog.controller.admin
 * Description：
 * Author: simpleM
 * Date: 2020/10/20 21:32
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "admin/blog-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    private BlogServiceImpl blogService;

    @Autowired
    private TagService tagService;

    @Autowired
    private TypeService typeService;

    private void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("tags", tagService.findAllTags());
    }
    @GetMapping("/blogs")
    public String showBlog(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        String orderBy = "update_time desc";//排序规则(* desc)按*降序
        PageHelper.startPage(pageNum,5,orderBy);//分页
        List<Blog> blogList =  blogService.getAllBlog();
        System.out.println(blogList);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);//将listType封装到PageInfo中
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("types", typeService.getAllType());//查询博客的类型和分类
        return LIST;
    }

    @PostMapping("/blogs/search") //按条件查询博客
    public String searchBlogs(Model model, @RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum, Blog blog){
        PageHelper.startPage(pageNum, 5);
        List<Blog> allBlog = blogService.searchAllBlog(blog);
        //得到分页结果对象
        PageInfo<Blog> pageInfo = new PageInfo<>(allBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("message", "查询成功");
        setTypeAndTag(model);
        return "admin/blogs";
    }

    @GetMapping("/blogs/input")
    public String toAddBlog(Model model){
        Blog blog = new Blog();
        model.addAttribute("blog",blog);
        setTypeAndTag(model);
        return INPUT;
    }
}
