package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

/**
 * FileName: UserService
 * Date: 2021/1/16 2:53 下午
 * Description: User服务类
 * @Author：guycui
 */
@Service
public class UserService {
    public User getUserInfo(){
        User user = new User();
        user.setName("GuyCui");
        user.setAge(21);
        return user;
    }
}
