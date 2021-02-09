package com.learn.blog_demo.service.impl;

import com.learn.blog_demo.dao.LabelRepository;
import com.learn.blog_demo.entity.Label;
import com.learn.blog_demo.exceptions.NotFoundException;
import com.learn.blog_demo.service.LabelService;
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
 * @create 2021-02-08 18:06
 */
@Service
public class LabelSeviceImpl implements LabelService {

    @Autowired
    private LabelRepository labelRepository;

    @Override
    @Transactional
    public Label saveLabel(Label label) {
        return labelRepository.save(label);
    }

    @Override
    @Transactional
    public Label getLabel(Long id) {
        return labelRepository.getOne(id);
    }

    @Override
    @Transactional
    public Label getLabelByName(String name) {
        return labelRepository.findByName(name);
    }

    @Override
    @Transactional
    public Page<Label> listLabel(Pageable pageable) {
        return labelRepository.findAll(pageable);
    }

    @Override
    public List<Label> listLabel() {
        return labelRepository.findAll();
    }

    @Override
    @Transactional
    public Label updateLabel(Long id, Label label) {
        Label l = labelRepository.getOne(id);

        if(l == null) {
            throw new NotFoundException("Label id does not exist");
        }
        BeanUtils.copyProperties(label, l);

        return labelRepository.save(l);
    }

    @Override
    @Transactional
    public void deleteLabel(Long id) {
        labelRepository.deleteById(id);
    }
}