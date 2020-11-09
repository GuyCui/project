package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

public class AritcleController {
    @Resource
    private ArticeRepository articeRepository;
    /**
     * Description:新增保存方法
     */
    @PostMapping("")
    public String saveArtice(Article model){
        articeRepository.save(model);
        return "redirect:/article/";
    }
}
