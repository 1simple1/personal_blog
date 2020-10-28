package com.simplem.personal_blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simplem.personal_blog.model.Type;
import com.simplem.personal_blog.service.BlogService;
import com.simplem.personal_blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * ClassName: TypeController
 * Package: com.simplem.personal_blog.controller.admin
 * Description：
 * Author: simpleM
 * Date: 2020/10/22 10:01
 */
@Configuration
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;
    //进入标签页
    @GetMapping("/types")
    public String getTypes(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        String orderBy = "id desc";//排序规则(id desc)按id降序
        PageHelper.startPage(pageNum,5,orderBy);//分页
        List<Type> typeList = typeService.getAllType();//获取所有的分类
        PageInfo<Type> pageInfo = new PageInfo<>(typeList);//将listType封装到PageInfo中
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    //去新增标签
    @GetMapping("/types/input")
    public String toAddType(Model model){
        model.addAttribute("type",new Type());//返回一个空type回去,防止前端渲染抱错
        return "admin/type-input";
    }

    //编辑标签
    @GetMapping("/types/{id}/input")
    public String editorType(@PathVariable Long id,Model model){
        model.addAttribute("type",typeService.selectById(id));
        return "admin/type-input";
    }

    //新增标签完成后返回标签列表
    @PostMapping("/types")
    public String addType(Type type,RedirectAttributes attributes){
        //要先判断新增的分类名是否已存在
        Type t = typeService.selectByName(type.getName());
        if (null != t){
            attributes.addFlashAttribute("message","新增失败，该分类已存在！");
            return "redirect:/admin/types/input"; //返回新增页面，重新新增
        }

        if(1 == typeService.insertType(type)){
            attributes.addFlashAttribute("success","新增成功！");
        }else {
            attributes.addFlashAttribute("error","新增失败");
        }
        return "redirect:/admin/types";
    }
    //修改完成后的分类
    @PostMapping("/types/{id}")
    public String changeType(Type type,@PathVariable Long id,RedirectAttributes attributes,Model model){
        //先判断修改的分类有没有重名
        Type t = typeService.selectByName(type.getName());//通过分类查询分类
        if (null != t){//如果存在，修改失败
            attributes.addFlashAttribute("error","修改失败，该分类已存在！");
            return "redirect:/admin/types/"+id+"/input"; //返回编辑页面，重新编辑
        }
        if(1 == typeService.updateType(type)){
            attributes.addFlashAttribute("success","修改成功");
        }else {
            attributes.addFlashAttribute("error","修改失败");
        }
        return "redirect:/admin/types";//必须要重定向到分类页面，没有pageInfo的数据，页面渲染报错
    }

    @GetMapping("/types/{id}/delete")
    public String deleteType(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("success","删除成功");
        return "redirect:/admin/types";

        /*Blog blog = blogService.selectBlogById(id);
        if(blog == null){
            typeService.deleteType(id);
            attributes.addFlashAttribute("success","删除成功");
            return "redirect:/admin/types";
        }else {
            attributes.addFlashAttribute("error","该博客下存在分类，不能删除！");
            return "redirect:/admin/types";
        }*/
    }

}
