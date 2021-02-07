package com.learn.blog_demo.web.admin;

import com.learn.blog_demo.entity.Type;
import com.learn.blog_demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


/**
 * @author Xiaotian
 * @program spring_boot_blog_demo
 * @description
 * @create 2021-02-07 12:10
 */
@Controller
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String categories(@PageableDefault(
            size = 5,
            sort = {"id"},
            direction = Sort.Direction.DESC)
                                         Pageable pageable,
                                         Model model) {

        model.addAttribute("page", categoryService.listType(pageable));
        return "admin/categories";
    }

    @GetMapping("/categories/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/category-input";
    }

    @PostMapping("/categories")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
        Type t = categoryService.getTypeByName(type.getName());
        if(t != null) {
            result.rejectValue("name", "nameError", "No Duplicates!");
            t = null;
        }

        if(result.hasErrors()) {
            return "admin/category-input";
        }
        Type type1 = categoryService.saveType(type);
        if(type1 == null) {
            attributes.addFlashAttribute("message", "Operation Failed");
        } else {
            attributes.addFlashAttribute("message", "Success!");
        }

        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", categoryService.getType(id));
        return "admin/category-input";
    }

    @PostMapping("/categories/{id}")
    public String editPost(@Valid Type type, BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {
        Type t = categoryService.getTypeByName(type.getName());
        if(t != null) {
            result.rejectValue("name", "nameError", "No Duplicates!");
            t = null;
        }

        if(result.hasErrors()) {
            return "admin/category-input";
        }
        Type type1 = categoryService.updateType(id, type);
        if(type1 == null) {
            attributes.addFlashAttribute("message", "Updating Failed");
        } else {
            attributes.addFlashAttribute("message", "Success!");
        }

        return "redirect:/admin/categories";
    }

    @GetMapping("categories/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        categoryService.deleteType(id);
        attributes.addFlashAttribute("message", "Deleted!");
        return "redirect:/admin/categories";
    }

}