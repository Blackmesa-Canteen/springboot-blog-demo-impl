package com.learn.blog_demo.service;

import com.learn.blog_demo.entity.Label;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LabelService {

    Label saveLabel(Label label);

    Label getLabel(Long id);

    Label getLabelByName(String name);

    Page<Label> listLabel(Pageable pageable);

    Label updateLabel(Long id, Label label);

    void deleteLabel(Long id);

}
