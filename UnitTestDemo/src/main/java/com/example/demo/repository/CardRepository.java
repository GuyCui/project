package com.example.demo.repository;

import com.example.demo.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * FileName: CardRepository
 * Date: 2021/1/16 3:34 下午
 * Description: 数据操作层
 * @Author：guycui
 */
public interface CardRepository extends JpaRepository<Card,Long>{
    Card findById(long id);
}
