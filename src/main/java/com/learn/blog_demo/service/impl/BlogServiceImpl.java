package com.learn.blog_demo.service.impl;

import com.learn.blog_demo.dao.BlogRepository;
import com.learn.blog_demo.entity.Blog;
import com.learn.blog_demo.entity.Type;
import com.learn.blog_demo.exceptions.NotFoundException;
import com.learn.blog_demo.service.BlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xiaotian
 * @program spring_boot_blog_demo
 * @description
 * @create 2021-02-08 18:50
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, Blog blog) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && null != blog.getTitle()) {
                    predicates.add(cb.like(root.<String>get("title"), "%" + blog.getTitle() + "%"));
                }

                if (null != blog.getType().getId()) {
                    predicates.add(cb.equal(root.<Type>get("type").get("id"), blog.getType().getId()));
                }

                if (blog.isRecommended()) {
                    predicates.add(cb.<Boolean>equal(root.get("isRecommended"), blog.isRecommended()));
                }

                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);
    }

    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogRepository.getOne(id);
        if( b == null) {
            throw new NotFoundException("Blog not found!");
        }
        BeanUtils.copyProperties(blog, b);

        return blogRepository.save(b);
    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}