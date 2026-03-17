# MyBatis-Learning

MyBatis 学习之路

## v1 搭建体验Mybatis
搭建了环境，链接数据库，用java访问数据库，在 `pom.xml` 下面装了依赖，resource 下面装了 `mybatis-config.xml`

类所对应的Mapper要在 config里面注册一下

## v2 Mapper代理开发
如果我们有很多个数据类的话，我们可以吧所有的 xml 文件放在resource下面对应的文件夹下（即形成对应的映射文件）