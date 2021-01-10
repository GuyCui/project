# Spring Boot 实战派

-- -

## Spring、Spring Boot、Spring Cloud 的关系

### 1.Spring

 	Spring 框架(为解决企业应用开发的复杂性而创建的框架)为开发 Java 应用程序提供了全面的基础架构支持。它提供了依赖注入和“开箱即用”的一些模块，如 Spring MVC、Spring JDBC、Spring Security、Spring AOP、Spring IOC、Spring ORM、Spring Test。这些模块大大地缩短了应用程序的开发时间，提高了开关应用程序的效率。

​	在 Spring 出现之前，如果要进行 Java Web 开发，则非常复杂，例如，若需要将记录插入数据库，则必须编写大量的代码来实现打开、操作和关闭数据库。而通过使用 Spring JDBC 模块的 JDBCTemplate，只需要进行数据操作即可，打开和关闭交由 Spring 管理。而且实现这些数据操作只需要配置几行代码。

-- -

### 2.Spring Boot

Spring Boot 是 Spring 框架的扩展和自动化，它消除了在 Spring 中需要进行的 XML(EXtensible Markup Language)文件配置(若习惯 XML配置，则依然可以使用)，使得开发变得更快、更高效、更自动化。

-- -

### 3.Spring Cloud

​	Spring Cloud 是一套分布式服务治理框架，它本身不提供具体功能性的操作，只专注服务之间的通信、熔断和监控等。因此，需要很多组件来共同支持一套功能。Spring Cloud 主要用于开发微服务。

​	微服务是可以独立部署、水平扩展、独立访问的服务单元。Spring Cloud 是这些微服务的“CTO(Chief Technical Officer)”，它提供各种方案来维护整个生态。

-- -

### 4.三者的关系

从上面三者的介绍中可以看出，Spring Boot 其实是要依赖 Spring 的，并不是另起炉灶创建了一个全新的框架，它是 Spring 的自动化。Spring Cloud 通过依赖 Spring Boot 来构建微服务应用。三者关系如图1-1 所示：
![](https://gitee.com/GuyCui/img/raw/master/img/SpringCloud、SpringBoot、Spring 关系依赖图.png)

-- -

Bean(豆子的意思，可以理解为 Java 类。Java 的名字来源于程序员经常喝的一种咖啡“爪哇”。这种咖啡使用“爪哇豆”磨出来的。所以，他们用“豆”来命名类。Java 语言中的许多类名称，多与咖啡有关，如咖啡豆—JavaBeans、网络豆—NetBeans 和对象豆—ObjectBeans)。

-- -

##  熟悉 Maven

### 2.2.2 认识其中的 pom.xml 文件

​	POM(Project Object Model，项目对象模型)是 Maven 工程的基本工作单元，也是 Maven 的核心。它是一个 XML 文件，包含项目的基本信息，用于描述项目如何构建、声明项目依赖等。

​	在执行任务或目标是时，Maven 会先在当前目录中查找 pom.xml 文件，然后获取所需的配置信息，再执行目标。

​	POM 中通常有以下元素。

#### 1.dependencies

在此元素下添加依赖，它可以包含多个<dependency>依赖

#### 2.dependency

<dependency>与</dependency>之间有 3 个标识，分别如下。

* groupId：定义隶属的实际项目，坐标元素之一。
* artifctId：定义项目中的一个模块，坐标元素之一。
* version：依赖或项目的版本，坐标元素之一。

<groupId>加上<artifactId>能标识唯一的项目或库。

dependency 用来申明依赖。如需要添加依赖，则可以在“<dependencies>”和“</dependencies>”元素之间进行添加。如需要添加 Web 的 Starter 依赖，则见以下代码：

```xml
<dependencies>
	<dependency>
  	<groupId>org.springframework.boot</groupId>
    <artifctId>spring-boot-starter-web</artifctId>
  </dependency>
</dependencies>
```

#### 3.scope

​	如果有一个在编译时需要而而发布时不需要的 JAR 包，则可以用 scope 标签标记该包，并将其值设置为 provided。

scope 标签的参数见表 2-1.

​																										**表2-1 scope 标签的参数** 

|   参数   |                             描述                             |
| :------: | :----------------------------------------------------------: |
| compile  | scope 的默认值，表示被依赖项目需要参与当前项目的编译、测试、运行阶段，是一个比较强的依赖。打包时也要包含进去。 |
| provided | provided 表示打包时可以不用打包进去，Web Container 会提供。该依赖理论上可以参与编译、测试、运行等周期。 |
| runtime  | 表示 dependency 不用在编译阶段，但会用作在运行和测试阶段，入 JDBC 驱动适用于运行和测试阶段。 |
|  system  | 和 provided 相似，但是在系统中要以外部 JAR 包的形式提供，Maven 不会在 repository 中查找它。 |
|   test   | 表示 dependency 作用在测试阶段，不用在运行阶段。只在测试阶段使用，用预编译和运行测试代码。不会随项目发布。 |

#### 4.properties

如果要使用自定义的变量，则可以在<properties></properties>元素中进行变量的定义，然后在其他节点中引用变量。它的好处是：在依赖配置时引用变量，可以达到统一版本号的目的。

例如，要定义 Java 和 Solr 的版本，可以通过以下代码实现：

```xml
<propertes>
	<java.version>1.8</java.version>
  <solr.version>8.0.0</solr.version>
</propertes>
```

要使用上面定义的变量，可以通过表达式“**${变量名}**”来调用：

```xml
<dependency>
	<groupId>org.apache.solr</groupId>
  <artifctId>solr-solrj</artifctId>
  <version>${solr.version}</version>
</dependency>
```

#### 5.plugin

在创建 Spring Boot 项目时，默认提供了 spring-boot-maven-plugin 插件。它提供打包时需要的信息，将 Spring Boot应用打包为可执行的 JAR 或 WAR 文件。

#### 6.完整的 pom.xml 文件

下面是一个完整的 pom.xml 文件，各个元素的详细用法说明见此文件的注释部分(符号“<!--”与“-->”之间的内容)。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<!-- 模型版本，声明项目描述符遵循哪一个POM模型版本，
	尽管模型本身的版本很少改变，但是它仍然是必不可少的， 这是为了当Maven引入了新的特性或者其他模型变更的时候，确保稳定性。 -->
	<modelVersion>4.0.0</modelVersion>
	<!-- 父项目的坐标，如果项目没规定某个元素的值，那么父项目中的对应值即为项目的默认值，坐标包括groupId，artifactId和version。-->
	<parent>

		<!--  被继承的父项目的唯一标识符 -->
		<groupId>org.springframework.boot</groupId>
		<!--  被继承的父项目的构件标识符 -->
		<artifactId>spring-boot-starter-parent</artifactId>
		<!--  被继承的父项目的版本号 -->
		<version>2.1.3.RELEASE</version>
		<!-- 父项目的pom.xml文件的相对路径，相对路径允许是一个不同的路径，默认值是../pom.xml
		Maven先在构建当前项目的地方寻找父项目的pom，然后在文件系统的relativePath位置，如果没找到继续在本地仓库，最后在远程仓库寻找父项目的pom。 -->
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<!-- 公司或者组织的唯一标志（项目的全球唯一标识符），并且配置时生成的路径也是由此生成，通常使用全限定的包名区分该项目和其他项目
		 如com.companyname.project，maven会将该项目打成的jar包放本地路径：/com/companyname/project -->
	<groupId>com.example</groupId>

	<!-- 项目的唯一ID，一个groupId下面可能多个项目，靠artifactId来区分 -->
	<artifactId>demo</artifactId>
	<!-- 版本号，格式为:主版本.次版本.增量版本-限定版本号 -->
	<version>0.0.1-SNAPSHOT</version>
	<!--项目的名称, Maven产生的文档用 -->
	<name>HelloWorld</name>
	<!-- 项目的详细描述, Maven 产生的文档用。 当这个元素能够用HTML格式描述时（例如，CDATA中的文本会被解析器忽略，就可以包含HTML标
        签）。 -->
	<description>Demo project for Spring Boot</description>

	<properties>
		<!-- 项目开发的java版本号。 -->
		<java.version>1.8</java.version>
	</properties>
	<!-- 项目的依赖项，可以通过，该元素描述了项目相关的所有依赖，它们自动从项目定义的仓库中下载。 -->
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
	<!-- 构建项目（打包生成可执行文件）需要的信息 -->
	<build>
		<!-- 项目使用的插件列表 。 -->
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>
```

-- -

### 2.2.3 Maven 的运作方式

Maven 会自动根据 dependencies 里面的依赖项，直接从 Maven 仓库中下载依赖到本地的".m2"目录下。

依赖的写法不需要记忆，推荐直接收藏官网中的内容，或在浏览器中搜索“mvnre”然后进行查询。根据需要输入依赖名进行搜索，比如要使用 Lombok，直接在搜索框中输入“Lombok”就会出现结果，结果会包含完整的 dependency(依赖)信息，如：

```xml
<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.8</version>
    <scope>provided</scope>
</dependency>
```

把上面的结果复制到 pom.xml 文件中的<dependencies></dependencies>之间即可使用。

在实际项目中，如果要手动添加 Maven 仓库中没有的 JAR 包依赖，则需要运行“mvn install:install-file”命令，以使 Maven 提供支持。如，添加 kaptcha 验证码依赖时，需要运行以下命令：

```shell
mvn install:install-file -Dfile=l:\jar包目录 -DgroupId=kcDgroupId -DartifactId=kcartifactId -Dversion=版本号 -Dpackaging=jar
```

-- -

## 使用开发工具

### 3.1.4 安装插件 Lombok

#### 3.Lombok 注解简介

在项目开发过程中需要使用注解来开启 Lombok 相应的功能，其注解及对应功能如下。

* @Data：自动生成 Getter/Setter、toString、equals、hashCode 方法，以及不带参数的构造方法。
* @NonNull：帮助处理 NullPointerException。
* @CleanUp：自动管理资源，不用再在 finally 中添加资源的 close 方法。
* @Setter/@Getter：自动生成 Getter/Setter方法。
* @ToString：自动生成 toString 方法。
* @EqualsAndHashcode：从对象的字段中重写 hashCode 和 equals 方法。
* @NoArgsConstructor/@RequiredArgsConstructor/@AllArgsConstructor:自动生成构造方法。
* @Value:用于注解 final 类。
* @Builder：产生复杂的构建器 API 类。
* @SneakyThrows：用于处理异常。
* Synchronized：同步方法的转化。
* @Log：支持使用各种日志(logger)对象。只要在使用时，用对应的注解进行标注，比如使用 Log4j 作为日志库，则在需要加入日志的位置写上注解@Log4j 即可。

-- -

## Spring Boot基础

### 了解 Spring Boot

#### 4.1.3 了解 Spring Boot 的自动配置

Spring Boot会根据配置的依赖信息进行自动配置，从而减轻开发者搭建环境和配置的负担。如果在项目中依赖了 spring-boot-starter-web，则 Spring Boot 会自动配置 Web 环境(配置 Tomcat、WebMVC、Validator、JSON 等)。

Spring Boot 自动配置是通过注解@EnableAutoConfiguration来实现的，具有非入侵性。如果要查看当前有哪些自动配置，则可以使用下方的“debug”调试命令：

```shell
java -jar *.jar -debug
```

如果在 Idea 中进行开发，则可以点击“run→EditConfigurations”命令：

![image-20201108202224713](https://gitee.com/GuyCui/img/raw/master/img/idea%20%E8%AE%BE%E7%BD%AE--debug%20%E8%B0%83%E8%AF%95%E5%91%BD%E4%BB%A4.png)

在启动应用程序后，在控制台即可看到条件评估报告(CONDITIONS EVALUATION REPORP)。

如果不需要某些自动配置，则可以通过注解@EnableAutoConfiguration 的“exclude或 excludeName”属性来指定，或在配置文件(application.properties 或 application.yml)中指定“spring.autoconfigure.exclude”的值。

-- -

#### 4.1.4 了解 Spring Boot 热部署

Spring Boot 热部署是为了更好的支持调试，在项目进行修改之后不需要耗费时间重启，在应用程序正常运行的情况下即可实时生效，以节约时间和操作。要实现热部署，则需要添加下方的热部署的依赖：

```xml
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
</dependency>
```

该依赖在项目打包时会被禁用。如果用“Java -jar”命令启动应用程序，或使用一个特定的 classloader 启动应用程序，则 Spring Boot 会认为这是一个“生产环境”，所以不会运行。如果项目中使用了 Redis 作为缓存，则请禁止使用热部署，以免出现类型转换等问题。

-- -

#### 定制启动画面

在 resources 资源目录下新建 banner.txt文件，然后在其中输入想要显示的文字。

在 banner.txt 文件中还可以加入一些属性信息：

```txt
${AnsiColor.BRIGHT_RED}：设置控制台中输出内容的颜色，具体可以参考 org.spring.framework.boot.ansi.AnsiColor。
${application.version}:用来获取 MANIFEST.MF文件中的版本号。
${application.formattrd-version}:格式化后的{application.version}版本信息。
${spring-boot.version}:SpringBoot版本号。
${spring-boot.formatted-version}:格式化后的{spring-boot.version}版本信息。
```

##### 关闭 Banner

如果不喜欢这种信息，想要关闭它，则可以通过修改入口类的“main”方法来实现，见以下代码：

```java
public static void main(String[] args) {
//    SpringApplication.run(HelloSpringBootApplication.class, args);
      SpringApplication springApplication = new SpringApplication(HelloSpringBootApplication.class);
      springApplication.setBannerMode(Banner.Mode.OFF);
      springApplication.run(args);
   }
```

其中，“setBannerMode(Banner.Mode.OFF)”代表 Banner 模式关闭。也可以在 application.properties 配置文件中配置下方代码关闭 Banner 模式：

```properties
spring.main.banner-mode=off
```

-- -

### 4.2 Spring Boot 常用注解

未来框架的趋势是“约定大于配置”，所以注解式编程会被更加广泛地使用。

#### 什么是注解式编程

注解(annotations)用来定义一个类、属性或一个方法，以便程序能被编译处理。它相当于一个说明文件，告诉应用程序某个被注解的类或属性是什么，要怎么处理。注解可以用于标注包、类、方法和变量等。

下方代码中的注解@RestController，是一个用来定义 Rest 风格的控制器。其中，注解@GetMapping("/hello")定义的访问路径是"/hello"。

```java
@RestController
public class SpringBootController {
    @RequestMapping("/hello")
    public String hello(){
    	return "Hello,Spring Boot!";
    }
}
```

#### 了解系统注解

系统注解见表 4-1。

​																													**表 4-1 系统注解**

|       注解        |                             说明                             |
| :---------------: | :----------------------------------------------------------: |
|     @Override     |           用于修饰方法，表示此方法重写了父类方法。           |
|    @Deprecated    | 用于修饰方法，表示此方法已经过时。经常在版本更新升级后遇到。 |
| @SuppressWarnings |                 告诉编译器忽视某类编译警告。                 |

下面重点介绍一下@SuppressWarnings 注解。它有以下几种属性。

* unchecked：未检查的转化。
* unused：未使用的变量。
* resource：泛型，即未指定类型。
* path：在类中的路径。原文件路径中有不存在的路径。
* deprecation：使用了某些不赞成使用的类和方法。
* fallthrough：switch 语句执行到底，不会遇到 break 关键字。
* serial：实现了 Serializable，但未定义 serialVersionUID。
* rawtypes：没有传递带有泛型的参数。
* all：代表全部类型的警告。



#### Spring Boot 的常用注解

##### 使用在类名上的注解

表 4-2 中列出了是用在类名上的注解。

​																									**表 4-2 使用在类名上的注解**

|      注解       |           使用位置           |                             说明                             |
| :-------------: | :--------------------------: | :----------------------------------------------------------: |
| @RestController |            类名上            |            作用相当于@ResponseBody 加@Controller             |
|   @Controller   |            类名上            |           声明此类是一个 SpringMVC Controller 对象           |
|    @Service     |            类名上            |              声明一个业务处理类（实现非接口类）              |
|   @Repository   |            类名上            |               声明数据库访问类（实现非接口类）               |
|   @Component    |            类名上            | 代表其是 Spring 管理类，常用在无法用@Service、@Repository 描述的 Spring 管理的类上，相当于通用的注解。 |
| @Configuration  |            类名上            |           声明此类是一个配置类，常于@Bean 配合使用           |
|    @Resource    | 类名上、属性或构造函数参数上 |                    默认按byName 自动注入                     |
|   @Autowired    | 类名上、属性或构造函数参数上 |                    默认按 byType 自动注入                    |
| @RequestMapping |        类名上或方法上        | 如果用在类上，则表示所有响应请求的方法都是以该地址作为父路径的 |
| @Transactional  |        类名上或方法上        |                         用于处理事务                         |
|   @Qualifier    |        类名上或属性上        |          为 Bean 指定名称，随后再通过名字引用 Bean           |

下面进一步讲解各个注解的知识点和用法。

（1）@RestController。

它用于返回 JSON(JavaScript Object Notation，JS 对象简谱)、XML(eXtensible Markup Language)等数据，但不能返回 HTML(HyperTest Markup Language)页面。相当于注解@ResponseBody 和注解 @Controller合在一起的作用。

(2) @Controller。

它用于标注控制器层，在 MVC 开发模式中代表 C (控制器)。

@Controller 主要用于构建 MVC 模式的程序。

(2)和(1)是一样的，也是返回 JSON 格式的数据。

例如，第一个实例程序输出的就是一个 JSON 格式的字符串“Hello，Spring Boot”，见一下代码：

```Java
@RestController
public class SpringBootController {
    @RequestMapping("/hello")
    public String hello(){
    	return "Hello,Spring Boot!";
    }
}
```

（3）@Service。

它用于声明一个业务处理类（实现非接口类）用于标注服务层，处理业务逻辑。

例如，以下代码就是集成 ArticleService 来实现其方法。

```java
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
```

（4）@Repository。

它用于标注数据访问层。

（5）@Component。

它用于把普通 POJO（Plain Ordinary JavaObjects，简单的 Java 对象）实例化到 Spring 容器中。当类不属于注解@Controller 和@Service 等时，就可以使用注解@Component 来标注这个类。它可配合 CommandLineRunner 使用，以便在程序启动后执行一些基础任务。

*Spring 会把被注解@Controller、@Service、@Repository、@Component 标注的类纳入 Spring 容器中进行管理。*

（6）@Configuration。

它用于标注配置类，并且可以由 Spring 容器自动处理。它作为Bean 的载体，用来指示一个类声明、一个或多个@Bean方法，在运行时为这些 Bean 生成 BeanDefinition 和服务请求。

（7）@Resource。

@Autowired 与@Resource 都可以用来装配 Bean，也都可以写在字段上或 Setter 方法上。

```java
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
```

(8) @Autowired。

它表示被修饰的类需要注入对象。Spring 会扫描所有被@Autowired 标注的类，然后根据类型在 IOC 容器中找到匹配的类进行注入。被@Autowired 注解后的类不需要再导入文件。

（9）@RequestMapping。

它用来处理请求地址映射，用在类或方法上。如果用在类上，则表示类中的所有响应请求的方法都是以该地址作为父路径的。该注解有 6 个属性。

* Params：指定 Request 中必须包含某些参数值，才让该方法处理。
* Headers：指定 Request 中必须包含某些指定的 header 值，才能让该方法处理请求。
* Value：指定请求的实际地址，指定的地址可以是 UPI Template 模式。
* Method：指定请求的 Method 类型，如 GET、POST、PUT、DELETE 等。
* Consumes：指定处理请求的提交内容类型 Content-Type，如“application/json,text/html”。
* Produces：指定返回的内容类型。只有当 Request 请求头中的 Accept 类型中包含该指定类型时才返回。

（10）@Transactional。

它可以用在接口、接口方法、类及类方法上。

但 Spring 不建议在接口或者接口方法上使用该注解，因为该注解只要在使用基于接口的代理时才会生效。如果异常类被捕获(try{}catch{})了，则事务就不回滚了。如果想让事务回滚，则必须再往外抛出异常(try{}catch{throw Exception})。

（11）@Qualifier。

它的意思是“合格者”，用于标注哪一个实现类才是需要注入的。需要注意的是，@Qualifier 的参数名称为被注入的类中的注解@Service 标注的名称。

@Qualifier 常和@Autowired 一起使用，如下代码：

```java
@Autowired
@Qualifier("articleService")
```

而@Resource 和它不同，@Resource 自带 Name 属性。

##### 使用在方法上的注解

表 4-3 列出了使用在方法上的主要注解。

​															**表 4-3 使用在方法上的注解**

|     注解      |  使用位置  |                             说明                             |
| :-----------: | :--------: | :----------------------------------------------------------: |
| @RequestBody  | 方法参数前 | 常用来处理 application/json、application/xml 等 Content-Type 类型的数据，意味着 HTTP 消息是 JSON/XML 格式，需将其转化为指定类型参数 |
| @PathVariable | 方法参数前 |              将 URL 获取的参数映射到方法参数上               |
|     @Bean     |   方法上   |     声明该方法的返回结果是一个由 Spring 容器管理的 Bean      |
| @ResponseBody |   方法上   | 通过适当的 HttpMessageConverter 将控制器中方法返回的对象转换为指定格式（JSON/XML）后，写入 Response 对象的 body 数据区 |

（1）@RequestBody。

他常用来处理 JSON/XML 格式的数据。通过@RequestBody 可以将请求体中的（JSON/XML）字符串绑定到相应的 Bean 上，也可以将其分别绑定到对应的字符串上。

举例：用 AJAX(前端)提交的数据，在控制器(后端)接收数据。

在前端页面使用 AJAX 提交数据的代码如下：

```js
$.ajax({
	url:"/post",
	type:"POST",
	data:'{"name":"longzhiran"}',
	// 这里不能写成 content-type
	contentType:"application/json charset=uft-8"
	success:function(data){
	alert("request success!");
	}
});
```

在控制器中接收数据的代码如下：

```java
@requestMapping("/post")
	public void post(@requesBody String name){
	// 省略
	}
```

（2）@PathVariable。

用于获取路径中的参数。

（3）@Bean。

它代表产生一个 Bean，并交给 Spring 管理。用于封装数据，一般有 Setter、Getter 方法。在 MVC 模型中，对应的是 M（模型）

（4）@ResponseBody

它的作用是通过转换器将控制方法返回的对象转换为指定的格式，然后写入 Response 对象的 body 区。它常用来返回 JSON/XML 格式的数据。

使用此注解后，数据直接写入输入流中，不需要进行视图渲染。用法见以下代码：

```
@GetMapping("/test")
@ResponseBody
public String test(){
return "test";
}
```

-- -

#### 使用配置文件

Spring Boot 支持使用 Properties 和 YAML 两种配置方式。两者功能类似，都能完成 Spring Boot 的配置，但是 Properties 的优先级要高于 YAML（YAML 语言的文件以“.yml”为后缀）

YAML文件的好处是——它采用的是树状结构，一目了然。但是，使用 YAML 配置方式时要注意一下几点：

* 原来以“.”分隔的 key 会变成树状结构。例如，“server.port=8080”会变成：

  ```yml
  server:
  	port: 8080
  ```

* 在 key 后的冒号一定要跟一个空格，如“port: 8080”。

* 如果把原有的 application.properties 删除，则建议执行以下"maven -X clean install"命令。

* YAML 格式不支持使用注解@PropertySource 导入配置。

  ##### 实例 3：演示如何使用 application.yml 文件

  本实例演示如何使用 application.yml 文件配置 Spring Boot 项目。注要对 Spring Boot 项目的端口、超时时间、参数值进行配置。

-- -

#### 分层开发Web 应用程序

#### 5.1.1了解 MVC 模式

Spring Boot 开发 Web 应用程序主要使用 MVC 模式。MVC 是 Model(模型)、View(视图)、Controller(控制器)的简写。

* Model：是 Java 的实体 Ben，代表存取数据的对象或 POJO(Plain Ordinary Java Object，简单的 Java 对象)，也可以带有逻辑。其作用是在内存中暂时存储数据，并在数据变化时更新控制器(如果要持久化，则需要把它写入数据库或者磁盘文件中)。
* View：主要用来解析、处理、显示内容，并进行模板的渲染。
* Controller：主要用来处理视图中的响应。他决定如何调用 Model(模型)的实体 Bean、如何调用业务层的数据增加、删除、修改和查询业务操作，以及如何将结果返给试图进行渲染。建议在控制器中尽量不放业务逻辑代码。

这样分层的好处是：将应用程序的用户界面和业务逻辑分离，使得代码具备良好的可扩展性、可复用性、可维护性和灵活性。

如果不想使用 MVC 进行开发模式也是可以的，MVC 只是一个非常合理的规范。MVC 的关系如图 5-1 所示。

![image-20201114151145770](https://gitee.com/GuyCui/img/raw/master/img/MVC%E6%A8%A1%E5%BC%8F.png)

MVC 开发模式是访问 DispatcherServlet 处理映射和调用视图渲染，然后返回给用户的数据。

在整个 SpringMVC 框架中，DispatcherServlet处于核心位置，继承自 HttpServlet。它负责协调和组织不同组件，以完成请求处理并返回响应工作。

整个工作流程如下：

​	（1）客户端(用户)发出请求由 Tomcat(服务器)接收，然后 Tomcat 将请求转交给 DispatcherServlet 处理。

​	（2）DispatcherServlet 匹配控制器中配置的映射路径，进行下一步处理。

​	（3）ViewResolver 将 ModelAndView 或 Exception 解析成 View。然后 View 会调用 render()方法，并根据 ModelAndView 中的数据渲染出页面。

在 MVC 开发模式中，容易混淆的还有 Model，它往往会被认为是业务逻辑层或 DAO 层。这种理解并不能说是错误的，但并不是严格意义上的 MVC 模式。

-- -

#### 5.1.2 MVC 和三层架构的关系

三层架构，就是将整个应用程序划分为表现层(UI)、业务逻辑层(Service)、数据访问层(DAO/Repository)。

* 表现层：用于展示页面。主要对用户的请求进行接收，以及进行数据的返回。它为客户端(用户)提供应用程序的访问接口(界面)。
* 业务逻辑层：是三层架构的服务层，负责业务逻辑处理，主要是调用 DAO 层对数据进行增加、删除、修改和查询等操作。
* 数据访问层：与数据库进行交互的持久层，被 Service 调用。在 Spring Data JPA 中由 Hibernate 来实现。

Repository 和 DAO 层一样，都可以进行数据的增加、删除、修改和查询。他们相当于仓库管理员，执行进/出货操作。

DAO 层的工作是存取对象。Repository 层的工作是存取和管理对象。

简单理解就是：Repository = 管理对象(对象缓存和在 Repository 的状态) + DAO。

严格来说，MVC 是三层架构中的 UI 层。通过 MVC 把三层架构中的 UI 层又进行了分层。由此可见，三层架构是基于业务逻辑或功能来划分的，而 MVC 是基于页面或功能来划分的。

--- -

#### 5.3 使用注解

在 Spring MVC 中，控制器(Controller)负责处理由 DispatcherServlet 接受并分发过来的请求。它把用户请求的数据通过业务处理层封装成一个 Model，然后再把改 Model 返回给对应的 View 进行展示。

Controller 无须继承特定的类或实现特定的接口。只需使用@Controller(@RestController)来标记一个控制器，然后用注解@RequestMapping 定义 URL 请求和 Controller 方法之间的映射，这样 Controller 就能被外界访问到。它可以包含多个请求处理方法。

GET 和 POST 的区别？

* Get 在浏览器中可以回退，而 POST 访问同一个地址时也是再次提交请求。
* Get 请求会被浏览器主动缓存，而 POST 则不会。
* Get 中的参数会被完整的保留在浏览器历史记录里，而 POST 中的参数则不会被保留。
* Get 只能进行 URL 编码，而 POST 支持多种编码方式。
* Get 只接受 ASCII 字符，而 POST 没有限制。
* Get 的安全性相比 POST 低，因为参数直接暴露在 URL 上，所以不能用它传递敏感信息。
* Get 的参数是通过 URL 传递的，而 POST 的参数是放在 request body 中的。但是，这些不是绝对的，比如 POST 也可以通过 URL 路径提交参数。

--- -

#### 7.3 认识过滤器

##### 哪些情况下会使用过滤器？

参数过滤、防止SQL注入、防止页面攻击、空参数矫正、Token 验证、Session 验证、点击率统计等。

##### 为什么使用过滤器？

在 web 开发中常常会有在接口中去除用户输入的非法字符，以防止引起业务异常。

* 在前端参数传入时进行校验，先过滤掉非法字符串，然后，返回用户界面提示用户重新输入。
* 后端接收前端没有过滤的数据，然后过滤非法字符。
* 利用 Filer 处理项目中所有非法字符。

很明显前两种实现方法会存在重复代码，因为每一个前端或后端都需要处理，这样会导致代码极难维护。如果用过滤器来实现，则只需要用过滤器对所有接口进行过滤处理。这样非常方便，同时不会出现冗余代码。

##### 使用 Filer 的步骤

（1）新建类，实现 Filer 抽象类。

（2）重写 init、doFilter、dostroy 方法。

（3）在 SpringBoot 入口中添加注解@ServletComponetScan，以注册 Filter。

在重写 3 个方法后，还可以进一步修改 request 参数使用的封装方式，如：

（1）编写 ParameterRequestWrapper类继承 HttpServletRequestWrapper 类。

（2）编写 ParameterRequestWrapper类构造器。

（3）在构造器中覆写父类构造器，并将 request.getParameterMap 加入子类的成员变量。

（4）编写 addParam 方法。

（5）修改参数并调用 ParameterRequestWrapper 实例，并保存 params。

（6）调用 doFilter 方法中的 FilterChain 变量，以重新封装修改后的 request。

