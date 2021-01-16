package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * FileName: Card
 * Date: 2021/1/16 3:25 下午
 * Description: 卡片实体
 * @Author：guycui
 */
@Entity
@Table(name = "cardtestjpa")
@Data
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer num;

}



