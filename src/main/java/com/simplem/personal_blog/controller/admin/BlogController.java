package com.simplem.personal_blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.simplem.personal_blog.service.impl.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @Autowired
    private BlogServiceImpl blogService;


}
