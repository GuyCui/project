package com.example.demo.controller;

import com.example.demo.entity.Article;
import com.example.demo.repository.ArticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * FileName: AritcleController
 * Date: 2021/1/17 4:08 下午
 * Description: 控制层
 * @Author：guycui
 */
@Controller
@RequestMapping("/article")
public class AritcleController {
    @Autowired
    private ArticeRepository articeRepository;

    @RequestMapping("")
    public ModelAndView articleList(@RequestParam(value = "start", defaultValue = "0") Integer start,
                                    @RequestParam(value = "limit", defaultValue = "5") Integer limit) {
        start = start < 0 ? 0 : start;
        List orders;
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, limit, sort);
        Page<Article> page = articeRepository.findAll(pageable);
        ModelAndView mav = new ModelAndView("article/list");
        return mav.addObject("page", page);
    }


}
