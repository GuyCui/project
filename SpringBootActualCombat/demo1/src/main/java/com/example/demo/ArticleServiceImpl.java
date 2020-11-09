package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description：标注为服务类
 */
@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleRepository articleRepository;
    /**
     * Description: 重写 Service 接口的实现，实现根据 ID 查询对象功能
     */
    @Override
    public Article findArticleById(long id){
        return articleRepository.findById(id);
    }
}
