package com.learn.blog_demo.dao;

import com.learn.blog_demo.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Xiaotian
 * @program spring_boot_blog_demo
 * @description
 * @create 2021-02-07 11:59
 */
public interface TypeRepository extends JpaRepository<Type, Long> {

    Type findByName(String name);

}