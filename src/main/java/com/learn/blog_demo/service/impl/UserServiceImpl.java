package com.learn.blog_demo.service.impl;

import com.learn.blog_demo.dao.UserRepository;
import com.learn.blog_demo.entity.User;
import com.learn.blog_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.learn.blog_demo.util.Md5Utils.getSaltverifyMd5AndSha;

/**
 * @author Xiaotian
 * @program spring_boot_blog_demo
 * @description
 * @create 2021-02-05 11:17
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            return null;
        }

        String record = user.getPassword();
        if (getSaltverifyMd5AndSha(password, record)) {
            return user;
        }
        return null;
    }
}