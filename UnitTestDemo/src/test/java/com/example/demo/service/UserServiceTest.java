package com.example.demo.service;

import com.example.demo.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * FileName: UserServiceTest
 * Date: 2021/1/16 2:56 下午
 * Description:
 * @Author：guycui
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

@Autowired
private UserService userService;

    @Test
    public void getUserInfo() {
        User user = userService.getUserInfo();
        Assert.assertEquals(20,user.getAge());
        Assert.assertThat(user.getName(),is("GuyCui"));
    }
}