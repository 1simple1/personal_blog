package com.simplem.personal_blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simplem.personal_blog.model.Tag;
import com.simplem.personal_blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * ClassName: TagController
 * Package: com.simplem.personal_blog.controller.admin
 * Description：
 * Author: simpleM
 * Date: 2020/10/22 20:03
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String showTags(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, Model model){
        String orderBy = "id desc";//排序规则(id desc)按id降序
        PageHelper.startPage(pageNum,5,orderBy);//分页
        List<Tag> tagList = tagService.findAllTags();
        PageInfo<Tag> pageInfo = new PageInfo<>(tagList);
        model.addAttribute("pageInfo",pageInfo);
        return "/admin/tags";
    }

    @GetMapping("/tags/input")
    public String toAddTags(Model model){
        model.addAttribute("tag",new Tag());//返回一个空type回去,防止前端渲染抱错
        return "admin/tag-input";
    }

    @GetMapping("/tags/{id}/input")
    public String editTag(@PathVariable Long id,Model model){
        Tag tag = tagService.findTagById(id);
        model.addAttribute("tag",tag);
        return "admin/tag-input";
    }

    @PostMapping("/tags")
    public String addTag(Tag tag, RedirectAttributes attributes){
        if( null != tagService.selectByName(tag.getName())){
            attributes.addFlashAttribute("message","新增失败，该标签已存在！");
            return "redirect:/admin/tags/input";
        }
        if(tagService.addTay(tag)==1){
            attributes.addFlashAttribute("message","新增成功");
            return "redirect:/admin/tags";
        }else {
            attributes.addFlashAttribute("message","新增失败");
        }
        return "redirect:/admin/tags";

    }

    @PostMapping("/tags/{id}")
    public String changeTag(RedirectAttributes attributes,@PathVariable Long id,Tag tag){
        if( null != tagService.selectByName(tag.getName())){
            attributes.addFlashAttribute("message","修改失败，该标签已存在！");
            return "redirect:/admin/tags/"+id+"/input";
        }
        if(1 == tagService.updateTag(tag)){
            attributes.addFlashAttribute("message","修改成功");
        }else {
            attributes.addFlashAttribute("message","修改失败");
        }
        return "redirect:/admin/tags";//必须要重定向到分类页面，没有pageInfo的数据，页面渲染报错
    }

    @GetMapping("/tags/{id}/delete")
    public String deleteType(@PathVariable Long id,RedirectAttributes attributes){
        tagService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }
}
