package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @FileName：HelloWorldController @Author：guycui @Date：2020/11/26 下午9:43 @Description：WebFlux 控制器
 */
@RestController
public class HelloWorldController {
  @GetMapping("/hello")
  public Mono<String> helloFlux() {
    return Mono.just("This is WebFlux demo");
  }
}
