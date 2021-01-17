package com.example.demo.repository;

import com.example.demo.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * FileName: ArticeRepository
 * Date: 2021/1/17 3:52 下午
 * Description: 数据持久层
 * @Author：guycui
 */
public interface ArticeRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {
    Article findById(long id);
}
