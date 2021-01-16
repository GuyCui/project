package com.example.demo.model;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * FileName: User
 * Date: 2021/1/16 11:14 下午
 * Description: user 实体类
 * @Author：guycui
 */
@Data
public class User implements RowMapper<User> {
    private int id;
    private String username;
    private String password;

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }
}
