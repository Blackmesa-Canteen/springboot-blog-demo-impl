package com.learn.blog_demo.web.admin;

import com.learn.blog_demo.entity.Blog;
import com.learn.blog_demo.entity.Type;
import com.learn.blog_demo.entity.User;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

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
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private LabelService labelService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 5, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable,
                                            BlogQuery blog,
                                            Model model) {

        model.addAttribute("types", categoryService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 5, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blog,
                        Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "/admin/blogs :: blogList";
    }


    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("types", categoryService.listType());
        model.addAttribute("labels", labelService.listLabel());
        return "admin/blog-input";
    }

    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(categoryService.getType(blog.getType().getId()));
        blog.setLabels(labelService.listLabel(blog.getLabelIds()));
        Blog b = blogService.saveBlog(blog);

        if(b == null) {
            attributes.addFlashAttribute("message", "Operation Failed");
        } else {
            attributes.addFlashAttribute("message", "Success!");
        }

        return REDIRECT_LIST;
    }


}