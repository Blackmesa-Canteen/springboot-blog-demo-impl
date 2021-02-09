package com.learn.blog_demo.web.admin;

import com.learn.blog_demo.entity.Blog;
import com.learn.blog_demo.service.BlogService;
import com.learn.blog_demo.service.CategoryService;
import com.learn.blog_demo.service.LabelService;
import com.learn.blog_demo.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    private static final String INPUT = "admin/blog-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "dedirect:/admin/blogs";

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private LabelService labelService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 2, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable,
                                            BlogQuery blog,
                                            Model model) {

        model.addAttribute("types", categoryService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 2, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blog,
                        Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "/admin/blogs :: blogList";
    }


    @GetMapping("blogs/input")
    public String input(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("types", categoryService.listType());
        model.addAttribute("labels", labelService.listLabel());
        return "admin/blog-input";
    }


}