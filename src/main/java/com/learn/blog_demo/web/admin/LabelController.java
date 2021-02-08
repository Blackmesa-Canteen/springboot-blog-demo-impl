package com.learn.blog_demo.web.admin;

import com.learn.blog_demo.entity.Label;
import com.learn.blog_demo.service.LabelService;
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
public class LabelController {

    @Autowired
    private LabelService labelService;

    @GetMapping("/labels")
    public String labels(@PageableDefault(
            size = 5,
            sort = {"id"},
            direction = Sort.Direction.DESC)
                                         Pageable pageable,
                                         Model model) {

        model.addAttribute("page", labelService.listLabel(pageable));
        return "admin/labels";
    }

    @GetMapping("/labels/input")
    public String input(Model model) {
        model.addAttribute("label", new Label());
        return "admin/label-input";
    }

    @PostMapping("/labels")
    public String post(@Valid Label label, BindingResult result, RedirectAttributes attributes) {
        Label  t = labelService.getLabelByName(label.getName());
        if(t != null) {
            result.rejectValue("name", "nameError", "No Duplicates!");
            t = null;
        }

        if(result.hasErrors()) {
            return "admin/label-input";
        }
        Label label1 = labelService.saveLabel(label);
        if(label1 == null) {
            attributes.addFlashAttribute("message", "Operation Failed");
        } else {
            attributes.addFlashAttribute("message", "Success!");
        }

        return "redirect:/admin/labels";
    }

    @GetMapping("/labels/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("label", labelService.getLabel(id));
        return "admin/label-input";
    }

    @PostMapping("/labels/{id}")
    public String editPost(@Valid Label label, BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {
        Label t = labelService.getLabelByName(label.getName());
        if(t != null) {
            result.rejectValue("name", "nameError", "No Duplicates!");
            t = null;
        }

        if(result.hasErrors()) {
            return "admin/label-input";
        }
        Label label1 = labelService.updateLabel(id, label);
        if(label1 == null) {
            attributes.addFlashAttribute("message", "Updating Failed");
        } else {
            attributes.addFlashAttribute("message", "Success!");
        }

        return "redirect:/admin/labels";
    }

    @GetMapping("labels/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        labelService.deleteLabel(id);
        attributes.addFlashAttribute("message", "Deleted!");
        return "redirect:/admin/labels";
    }

}