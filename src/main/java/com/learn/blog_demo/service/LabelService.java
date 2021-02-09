package com.learn.blog_demo.service;

import com.learn.blog_demo.entity.Label;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LabelService {

    Label saveLabel(Label label);

    Label getLabel(Long id);

    Label getLabelByName(String name);

    Page<Label> listLabel(Pageable pageable);

    List<Label> listLabel();

    Label updateLabel(Long id, Label label);

    void deleteLabel(Long id);

}
