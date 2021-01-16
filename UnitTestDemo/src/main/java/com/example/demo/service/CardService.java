package com.example.demo.service;

import com.example.demo.entity.Card;

import java.util.List;

/**
 * FileName: CardService
 * Date: 2021/1/16 3:39 下午
 * Description: 卡片服务层
 * @Author：guycui
 */
public interface CardService {
    public List<Card> getCardList();
    public Card findCardById(long id);
}
