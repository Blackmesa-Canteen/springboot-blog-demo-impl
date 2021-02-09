package com.learn.blog_demo.web;

import com.learn.blog_demo.exceptions.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Xiaotian
 * @program spring_boot_blog_demo
 * @description
 * @create 2021-02-03 23:21
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }

    @GetMapping("/archive")
    public String archive() {
        return "archive";
    }


    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/categories")
    public String categories() {
        return "categories";
    }

    @GetMapping("/labels")
    public String labels() {
        return "Labels";
    }

//    @GetMapping("/admin/blog-input")
//    public String blogInput() {
//        return "/admin/blog-input";
//    }

//    @GetMapping("/{id}/{name}")
//    public String test(@PathVariable Integer id, @PathVariable String name) {
////        int i = 9/0;
////        String blog = null;
////        if (blog == null) {
////            throw new NotFoundException("The blog not exist!");
////        }
//        System.out.println("----------index---------");
//        return "index";
//    }
}