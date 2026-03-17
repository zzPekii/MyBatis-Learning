# MyBatis-Learning

MyBatis 学习之路

## v1 搭建体验Mybatis
搭建了环境，链接数据库，用java访问数据库，在 `pom.xml` 下面装了依赖，resource 下面装了 `mybatis-config.xml`

类所对应的Mapper要在 config里面注册一下

## v2 Mapper代理开发
如果我们有很多个数据类的话，我们可以吧所有的 xml 文件放在resource下面对应的文件夹下（即形成对应的映射文件）

## v3 MyBatis的核心文件配置
主要学习了在 `config` 里面可以声明包，在对应的Mapper中的 `resultType` 书写就不需要那么麻烦了

## v4 配置文件下的CRUD
### select
学习了3中查询方式：查询所有数据，查询详情，条件查询
条件查询下有3种方式
1. 零零散散的条件传入
2. 封装成类
3. 封装成map