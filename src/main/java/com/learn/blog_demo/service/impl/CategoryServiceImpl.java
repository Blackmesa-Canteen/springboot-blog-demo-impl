package com.learn.blog_demo.service.impl;

import com.learn.blog_demo.dao.TypeRepository;
import com.learn.blog_demo.entity.Type;
import com.learn.blog_demo.exceptions.NotFoundException;
import com.learn.blog_demo.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Xiaotian
 * @program spring_boot_blog_demo
 * @description
 * @create 2021-02-07 11:58
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    @Transactional
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Override
    @Transactional
    public Type getType(Long id) {
        return typeRepository.getOne(id);
    }

    @Override
    @Transactional
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Override
    @Transactional
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public List<Type> listType() {

        return typeRepository.findAll();
    }

    @Override
    @Transactional
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.getOne(id);

        if(t == null) {
            throw new NotFoundException("category id does not exist");
        }
        BeanUtils.copyProperties(type, t);

        return typeRepository.save(t);
    }

    @Override
    @Transactional
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }
}