# 3. MyBatis 核心文件配置

## 3.1 环境配置

来配置数据库的环境信息

可以配置多个env链接多个数据库，方便切换不同的数据源

比如说还有测试库 test，等等

可以通过default属性切换不同的env

```xml
<environments default="development">
  <environment id="development">
    <transactionManager type="JDBC"/>
    <dataSource type="POOLED">
      <!--数据库的连接信息-->
          <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
          <property name="url" value="jdbc:mysql://localhost:3306/mybatis_test?useSSL=false&amp;serverTimezone=UTC&amp;allowPublicKeyRetrieval=true"/>
          <property name="username" value="root"/>
          <property name="password" value="Zzyisgenius"/>
    </dataSource>
  </environment>
</environments>
```

`transactionManager` 和 `dataSource` 不用管未来会背`SpringBoost`接管

## 3.2 类型别名配置

在配置`class.xml`的时候，我们可以在`config`中先声明包，这就相当于给所有pojo下面的类起了个别名。在写返回类型的时候就可以只写`user`（在对应的`xxxMapper.xml`中）

```xml
<!--在config中-->
<!--包扫描-->
<typeAliases>
	<package name="com.harris.pojo">
</typeAliases>
```

```xml
<mapper namespace="com.harris.mapper.UserMapper">

    <select id="selectAll" resultType="com.harris.pojo.User">
        SELECT * FROM user
    </select>

    <select id="selectById" resultType="com.harris.pojo.User">
        SELECT * FROM user WHERE id = #{id}
    </select>
</mapper>

<!--也就是修改resultType-->
<!--修改如下-->
<!--修改后类名也不需要区分大小-->
<mapper namespace="com.harris.mapper.UserMapper">

    <select id="selectAll" resultType="user">
        SELECT * FROM user
    </select>

    <select id="selectById" resultType="user">
        SELECT * FROM user WHERE id = #{id}
    </select>
</mapper>
```