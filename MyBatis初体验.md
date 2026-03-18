# 这是个啥玩意？

MyBatis是一款持久层框架，用于简化JDBC开发

Introduction：https://mybatis.org/mybatis-3/zh_CN/index.html

## 持久层

负责将数据保存到数据库的那一层代码

JavaEE三层架构：表现层、业务层、持久层

# 1. Introduction

## 查询user表中的所有数据

1. 创建user表，添加数据
2. 创建模块，导入坐标
3. 编写MyBatis核心配置文件 → 替换链接信息，解决硬编码问题
4. 编写SQL映射文件 → 统一管理sql语句 解决硬编码问题
5. 编码
    1. 定义`POJO` 类
    2. 家在核心配置文件，获取`SqlSessionFactory`对象
    3. 获取`SqlSessionFactory`对象，执行`SQL`语句
    4. 释放资源

### 1.1 启动docker启动sql

### 1.2 初始化Java Maven

在 VSC 中按下 `Ctrl+Shift+P` (Mac 是 `Cmd+Shift+P`)，输入： `Java: Create Java Project...` -> 选择 **Maven** -> 选择 **No Archetype**。

- **Group Id**: `com.harris`
- **Artifact Id**: `mybatis-demo`

### 1.3 配置 pom.xml（项目依赖）

打开新生成的 `pom.xml`，在 `<dependencies>` 里填入我们需要的东西：分别是mybatis依赖，mysql依赖，junit依赖

```xml
<dependencies>
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.13</version>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.9.2</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

这是 VSC 开发者最容易踩的坑。Java 的 XML 文件必须放在 `src/main/resources` 下，或者在 `pom.xml` 里手动指定。

为了保险，请在 `pom.xml` 的 `<build>` 标签中加入这段，确保你的 XML 不会被 Maven 忽略：

```xml
<build>
    <resources>
        <resource>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.xml</include>
                <include>**/*.properties</include>
            </includes>
        </resource>
    </resources>
</build>
```

### 1.4 编写 `mybatis-config.xml` 文件

在 VS Code 的项目目录中，确保路径如下： `src/main/resources/mybatis-config.xml`

如果 `resources` 文件夹不存在，请手动创建一个。

编写 `mybatis-config.xml` ，在MyBatis的网页中有默认的

主要是要修改本地的driver url password

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis_test?useSSL=false&amp;serverTimezone=UTC"/>
                <property name="username" value="root"/>
                <property name="password" value="你的密码"/>
            </dataSource>
        </environment>
    </environments>

    <mappers>
        <mapper resource="com/harris/mapper/UserMapper.xml"/>
    </mappers>

</configuration>
```

### 1.6 准备一个Java实体类（POJO）

在 `src/main/java/com/harris/pojo/` 下创建 `User.java`

*这就是数据库表在Java内的”镜像“*

```java
package com.harris.pojo;

public class User {
    private Integer id;
    private String username;
    private String password;

    // 快捷键提示：在 VSC 中可以右键选择 "Source Action..." 
    // 然后选择 "Generate Getters and Setters" 和 "Generate toString()"
}
```

### 1.7 写一个Demo类测一下

```java
package com.harris;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.harris.pojo.User;

public class Demo1 {
    public static void main(String[] args) throws IOException{

        // 1. 加载明艳把提升的核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession对象用来执行sql
        SqlSession sqlsession = sqlSessionFactory.openSession();

        // 3. 执行sql语句
        List<User> users = sqlsession.selectList("com.harris.mapper.UserMapper.selectAll");
        System.out.println(users);

        //4. 释放
        sqlsession.close();
    }
}

```

最终就会输出这张表的所有信息

# 2. Mapper 代理开发

# 3. MyBatis 核心文件配置

# 4. 配置文件完成增删查改

# 5. 注解完成增删查改
