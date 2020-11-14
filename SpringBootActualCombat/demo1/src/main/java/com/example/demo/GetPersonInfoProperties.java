package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Component: 此声明此类是 Spring 管理类。它常用在无法用@Service、@Repository 描述的 Spring
 * 管理类上，相当于通用类的注解。
 * @ConfigurationProperties (prefix = "personinfo"): 把同类配置信息自动封装成一个实体类。
 * 其属性 prefix 代表配置文件中配置项的前缀，如在配置文件中定义的“personinfo”。
 *
 * @author guycui
 */
 @Component
 @ConfigurationProperties(prefix = "personinfo")
public class GetPersonInfoProperties {
  private String name;
  private int age;

  /**
   * 还可以把 @ConfigurationProperties直接定义在@Bean 的注解里，这时 Bean 实体类就不需要@Component 和@ConfigurationProperties 注解了
   * @return GetPersonInfoProperties
   */
 // @Bean
 // @ConfigurationProperties(prefix = "personinfo")
  public GetPersonInfoProperties getPersonInfoProperties(){
    return new GetPersonInfoProperties();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
