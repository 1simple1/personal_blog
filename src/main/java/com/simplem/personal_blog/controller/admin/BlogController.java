package com.simplem.personal_blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simplem.personal_blog.model.Blog;
import com.simplem.personal_blog.model.User;
import com.simplem.personal_blog.service.TagService;
import com.simplem.personal_blog.service.TypeService;
import com.simplem.personal_blog.service.impl.BlogServiceImpl;
import com.simplem.personal_blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
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
        String orderBy = "update_time desc";//排序规则(* desc)按*降序 也可以直接加在SQL语句中
        PageHelper.startPage(pageNum,5,orderBy);//分页
        List<Blog> blogList =  blogService.getAllBlog();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);//将listType封装到PageInfo中
        model.addAttribute("blogPageInfo",pageInfo);
        model.addAttribute("types", typeService.getAllType());//查询博客的类型和分类
        return LIST;
    }

    @PostMapping("/blogs/search") //按条件查询博客
    public String searchBlogs(Model model , BlogQuery blogQuery,
                              @RequestParam(defaultValue = "1",value = "pageNum") int pageNum){
        PageHelper.startPage(pageNum, 5);
        List<Blog> allBlog = blogService.getAllBlogBySearch(blogQuery);
        //得到分页结果对象
        PageInfo<Blog> pageInfo = new PageInfo<>(allBlog);
        model.addAttribute("blogPageInfo", pageInfo);
        model.addAttribute("message", "查询成功");
        model.addAttribute("types", typeService.getAllType());
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String toAddBlog(Model model){
        Blog blog = new Blog();
        blog.setCommentabled(true);
        blog.setAppreciation(true);
        blog.setRecommend(true);
        blog.setShareStatement(true);
        model.addAttribute("blog",blog);
        setTypeAndTag(model);
        return INPUT;
    }

    @GetMapping("/blogs/{id}/input")
    public String editBlog(@PathVariable Long id,Model model){
        setTypeAndTag(model);
        Blog b = blogService.getBlogById(id);
        model.addAttribute("blog",b);
        return INPUT;
    }

    @PostMapping("/blogs")
    public String addBlog(Blog blog, RedirectAttributes redirectAttributes, HttpSession session){
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.selectById(blog.getType().getId())); //通过获取的typeid查询Type赋值给blog
        blog.setTags(tagService.listTag(blog.getTagIds())); //通过获取的tagids查询List<Tag>赋值给blog
        if( blog.getId() == null){     // 表示新增的blog
            int save = blogService.save(blog);
            if (save == 1) {
                redirectAttributes.addFlashAttribute("success", "保存成功！");
            } else {
                redirectAttributes.addFlashAttribute("error", "保存失败");
            }
            return REDIRECT_LIST;
        }else { //表示博客的修改
            int update = blogService.update(blog);
            if (update == 1) {
                redirectAttributes.addFlashAttribute("success", "修改成功！");
            } else {
                redirectAttributes.addFlashAttribute("error", "修改失败");
            }
        }
        return REDIRECT_LIST;
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        if( blogService.delete(id) > 0){
            attributes.addFlashAttribute("success","删除成功！");
        }else {
            attributes.addFlashAttribute("error","删除失败");

        }
        return REDIRECT_LIST;//删除完成后需要重定向到博客管理页面
    }

}
