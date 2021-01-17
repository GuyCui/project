package com.example.demo.service;

import com.example.demo.entity.Article;

import java.util.List;

/**
 * FileName: ArticleSercice
 * Date: 2021/1/17 4:02 下午
 * Description: 服务接口
 * @Author：guycui
 */
public interface ArticleSercice {
    List<Article> getArticleList();

    Article findArticleById(long id);
}
