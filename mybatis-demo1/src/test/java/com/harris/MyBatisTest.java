package com.harris;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import com.harris.mapper.UserMapper;
import com.harris.pojo.User;

import java.io.InputStream;
import java.util.List;

public class MyBatisTest {

    @Test
    public void testConnection() {
        try {
            // 1. 加载 mybatis-config.xml 配置文件
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);

            // 2. 构建 SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 3. 打开 SqlSession
            SqlSession session = sqlSessionFactory.openSession();
            
            System.out.println("成功！数据库连接已建立。Session 对象：" + session);
            
            // 4. 关闭资源
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败！请检查数据库是否启动或配置文件是否正确。");
        }
    }

    @Test
    public void testSelectAll() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            // 通过反射获取接口的代理对象
            UserMapper userMapper = session.getMapper(UserMapper.class);
            
            // 调用方法
            List<User> users = userMapper.selectAll();
            
            // 打印结果
            users.forEach(System.out::println);
        }
    }

    @Test
    public void testSelectById() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            
            // 传入 ID 为 1
            User user = userMapper.selectById(1);
            System.out.println("查询结果：" + user);
        }
    }
}