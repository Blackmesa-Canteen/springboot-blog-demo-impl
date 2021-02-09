package com.learn.blog_demo.web.admin;

import com.learn.blog_demo.entity.Blog;
import com.learn.blog_demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    private BlogService blogService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 2, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable,
                                            Blog blog,
                                            Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "/admin/blogs";
    }

    @GetMapping("/blogs/search")
    public String search(@PageableDefault(size = 2, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable,
                        Blog blog,
                        Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "/admin/blogs :: blogList";
    }


}