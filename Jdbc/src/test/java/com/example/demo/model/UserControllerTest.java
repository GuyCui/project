package com.example.demo.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * FileName: UserControllerTest
 * Date: 2021/1/16 11:20 下午
 * Description:
 * @Author：guycui
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    /*
     * 创建表
     */
    public void createUserTable()throws Exception{
        String sql = "CREATE TABLE `user`(\n"+
                "`id` int(10) NOT NULL AUTO_INCREMENT,\n"+
                "`username` varchar(100) DEFAULT NULL,\n"+
                "`password` varchar(100) DEFAULT NULL,\n"+
                "PRIMARY KEY(`id`)\n"+
                ")ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;\n"+
                "\n";
        jdbcTemplate.execute(sql);
    }
    @Test
    /*
     * 通过 update方法 新增数据
     */
    public void saveUserTest()throws Exception{
        String sql = "INSERT INTO user(USERNAME,PASSWORD) VALUES('root','root')";
        int rows = jdbcTemplate.update(sql);
        System.out.println(rows);
    }
    @Test
    /*
     * 通过 query方法 根据 username 查询数据
     */
    public void getUserByName(){
        String name = "GuyCui";
        String sql = "SELECT * FROM user WHERE USERNAME = ?";
        List<User> list = jdbcTemplate.query(sql, new User(), new Object[]{name});
        for (User user : list) {
            System.out.println(user);
        }
    }
    @Test
    /*
     * 通过 query方法 查询所有数据
     */
    public void list(){
        String sql = "SELECT * FROM user limit 100";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        for (User user : userList) {
            System.out.println(user);
        }
    }
    @Test
    /*
     * 通过 update方法 修改数据
     */
    public void updateUserPassword(){
        Integer id = 1;
        String password = "12345";
        String sql = "UPDATE user SET PASSWORD = ? WHERE ID = ?";
        int rows = jdbcTemplate.update(sql, password, id);
        System.out.println(rows);
    }
    @Test
    public void deleteUserById(){
        String sql = "DELETE FROM user WHERE ID = ?";
        int rows = jdbcTemplate.update(sql, 2);
        System.out.println(rows);
    }
}