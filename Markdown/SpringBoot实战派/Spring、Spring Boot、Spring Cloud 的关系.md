## Spring、Spring Boot、Spring Cloud 的关系

-- -

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

![](https://raw.githubusercontent.com/GuyCui/img/master/Spring%E3%80%81Spring%20Boot%E3%80%81Spring%20Cloud%20%E7%9A%84%E4%BE%9D%E8%B5%96%E5%85%B3%E7%B3%BB-20201107144819907.png)

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

实战