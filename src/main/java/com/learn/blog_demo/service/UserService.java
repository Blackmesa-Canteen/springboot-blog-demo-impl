package com.learn.blog_demo.service;

import com.learn.blog_demo.entity.User;

public interface UserService {

    User checkUser(String username, String password);
}
