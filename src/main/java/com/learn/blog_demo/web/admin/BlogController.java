package com.learn.blog_demo.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Xiaotian
 * @program spring_boot_blog_demo
 * @description
 * @create 2021-02-07 00:28
 */
@Controller
@RequestMapping("/admin")
public class BlogController {
    @GetMapping("/manager")
    public String list() {
        return "admin/manager";
    }
}