package com.simplem.personal_blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simplem.personal_blog.model.Type;
import com.simplem.personal_blog.service.BlogService;
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
 * ClassName: TypeController
 * Package: com.simplem.personal_blog.controller
 * Description：
 * Author: simpleM
 * Date: 2020/10/25 9:28
 */
@Controller
public class TypeShowController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/types/{typeId}")
    public String show(@PathVariable Long typeId, Model model,
                       @RequestParam(defaultValue = "1") int pageNum){
        PageHelper.startPage(pageNum, 100);  //必须开启分页
        List<Type> typeList = typeService.getIndexType();
        model.addAttribute("types",typeList);
        if(typeId == -1){ //表明是从首页过来的
            typeId = typeList.get(0).getId();//获取第一个分类的id，显示typeId下的所有文章
        }
        PageHelper.startPage(pageNum, 5);
        List<FirstPageBlog> blogList =  blogService.findAllByTypeId(typeId);
        PageInfo<FirstPageBlog> blogPageInfo = new PageInfo<>(blogList);
        model.addAttribute("blogPageInfo",blogPageInfo);
        return "type";
    }
}
