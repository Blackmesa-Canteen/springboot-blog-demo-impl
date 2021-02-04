package com.learn.blog_demo.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xiaotian
 * @program spring_boot_blog_demo
 * @description
 * @create 2021-02-05 00:13
 */
@Entity
@Table(name = "t_label")
public class Label {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "labels")
    private List<Blog> blogs = new ArrayList<>();

    public Label() {
    }

    public Label(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }


}