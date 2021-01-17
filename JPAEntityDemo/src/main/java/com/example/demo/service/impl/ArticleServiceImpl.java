package com.example.demo.service.impl;

import com.example.demo.entity.Article;
import com.example.demo.repository.ArticeRepository;
import com.example.demo.service.ArticleSercice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * FileName: ArticleServiceImpl
 * Date: 2021/1/17 4:05 下午
 * Description: 实现服务类
 * @Author：guycui
 */
@Service
public class ArticleServiceImpl implements ArticleSercice {
    @Autowired
    private ArticeRepository articeRepository;

    @Override
    public List<Article> getArticleList() {
        return articeRepository.findAll();
    }

    @Override
    public Article findArticleById(long id) {
        return articeRepository.findById(id);
    }
}
