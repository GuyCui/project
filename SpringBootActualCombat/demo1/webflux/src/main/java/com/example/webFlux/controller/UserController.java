package com.example.webFlux.controller;

import com.example.webFlux.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/** FileName: UserController @Author：guycui Date: 2020/11/27 下午7:52 Description: 控制层 */
@RestController
@RequestMapping(path = "/user")
public class UserController {
  Map<Long, User> users = new HashMap<>();

  /** 依赖关系注入完成之后，执行初始化。 */
  @PostConstruct
  public void init() {
    users.put(1L, new User(1L, "CuiGuy", 22));
    users.put(2L, new User(2L, "ChenGuy", 21));
  }

  /** 获取所有用户 */
  @GetMapping("/list")
  public Flux<User> getAll() {
    return Flux.fromIterable(new ArrayList<>(users.values()));
  }

  @GetMapping("/aop")
  public String aVoid() {
    return "hello aop test";
}

  /** 获取单个用户 */
  @GetMapping("/{id}")
  public Mono<User> getUser(@PathVariable Long id) {
    return Mono.justOrEmpty(users.get(id));
  }

  /** 创建用户 */
  @PostMapping("")
  public Mono<ResponseEntity<String>> addUser(User user) {
    users.put(user.getId(), user);
    return Mono.just(new ResponseEntity<>("添加成功", HttpStatus.CREATED));
  }

  /** 修改用户 */
  @PutMapping("/{id}")
  public Mono<ResponseEntity<User>> putUser(@PathVariable Long id, User user) {
    user.setId(id);
    users.put(id, user);
    return Mono.just(new ResponseEntity<>(user, HttpStatus.CREATED));
  }

  /** 删除用户 */
  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<String>> deleteUser(@PathVariable Long id) {
    users.remove(id);
    return Mono.just(new ResponseEntity<>("删除成功", HttpStatus.ACCEPTED));
  }
}
