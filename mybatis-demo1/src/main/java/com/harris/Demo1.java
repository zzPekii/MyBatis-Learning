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

        User u1 = sqlsession.selectOne("com.harris.mapper.UserMapper.selectById", 1);
        System.out.println(u1);
        //4. 释放
        sqlsession.close();
    }
}
