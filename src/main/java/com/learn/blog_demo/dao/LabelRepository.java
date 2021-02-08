package com.learn.blog_demo.dao;

import com.learn.blog_demo.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Xiaotian
 * @program spring_boot_blog_demo
 * @description
 * @create 2021-02-07 11:59
 */
public interface LabelRepository extends JpaRepository<Label, Long> {

    Label findByName(String name);

}