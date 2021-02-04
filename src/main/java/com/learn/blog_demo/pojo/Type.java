package com.learn.blog_demo.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xiaotian
 * @program spring_boot_blog_demo
 * @description
 * @create 2021-02-05 00:11
 */

@Entity
@Table(name = "t_type")
public class Type {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();

    public Type() {
    }

    public Type(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Type{" +
                "Id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}