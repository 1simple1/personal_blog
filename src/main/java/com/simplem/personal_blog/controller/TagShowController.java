package com.simplem.personal_blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simplem.personal_blog.model.Tag;
import com.simplem.personal_blog.service.BlogService;
import com.simplem.personal_blog.service.TagService;
import com.simplem.personal_blog.vo.FirstPageBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * ClassName: TagShowController
 * Package: com.simplem.personal_blog.controller
 * Description：
 * Author: simpleM
 * Date: 2020/10/25 16:57
 */
@Controller
public class TagShowController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    @GetMapping("/tags/{tagId}")
    public String show(@PathVariable Long tagId, Model model,
                       @RequestParam(defaultValue = "1") int pageNum){
        PageHelper.startPage(pageNum, 100);  //必须开启分页
        List<Tag> tagList = tagService.getIndexTag();
        model.addAttribute("tags",tagList);
        if(tagId == -1){ //表明是从首页过来的
            tagId = tagList.get(0).getId();//获取第一个分类的id，显示typeId下的所有文章
        }
        PageHelper.startPage(pageNum, 5);
        List<FirstPageBlog> blogList =  blogService.findAllByTagId(tagId);
        PageInfo<FirstPageBlog> blogPageInfo = new PageInfo<>(blogList);
        model.addAttribute("blogPageInfo",blogPageInfo);
        return "tag";
    }
}
