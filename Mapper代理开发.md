# 2. Mapper 代理开发

## 2.1 概况

在上面的调用中，我们把整个statement字符串传入`selectList()` 函数中，存在着硬编码的问题，

```java
// 3. 执行sql语句
List<User> users = sqlsession.selectList("com.harris.mapper.UserMapper.selectAll");
System.out.println(users);
```

我们可以通过在sqlSession里面直接获取Mapper类里面的方法，方便直接调用

```java
// 通过反射获取接口的代理对象
UserMapper userMapper = session.getMapper(UserMapper.class);
List<User> users = userMapper.selectAll();
users.forEach(System.out::println);
```

## 2.2 步骤

1. 定义与SQL映射文件同名的Mapper接口，并且Mapper接口和SQL映射文件放置在同一个目录下
2. 设置SQL映射文件的namespace属性为Mapper接口全限定名

![截屏2026-03-17 20.52.45.png](attachment:39355f18-a846-45e4-9d35-8084b5590d83:截屏2026-03-17_20.52.45.png)

像这样UserMapper class在resource的影射文件（潜规则class name要对应一样的xml文件）

1. 在Mapper接口中定义方法，方法名就是SQL映射文件中sql语句的id，并保持参数类型返回类型一致
2. Coding
    1. 通过Sql Session的getMapper方法获取 Mapper接口的代理对象
    2. 调用对应方法完成sql的执行
    3. *调用如上*