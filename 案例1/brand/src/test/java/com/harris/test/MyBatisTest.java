package com.harris.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import com.harris.mapper.BrandMapper;
import com.harris.pojo.Brand;

public class MyBatisTest {
    
    @Test
    public void testSelectAll() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        List<Brand> brands = brandMapper.selectAll();
        for (Brand b : brands) {
            System.out.println(b.toString());
        }
        System.out.println(brands.size());
        sqlSession.close();
    }

    @Test
    public void testSelectById() throws IOException {
        int id = 1;

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        Brand brand = brandMapper.selectById(id);
        System.out.println(brand);
        sqlSession.close();
    }

    @Test
    public void testSelectByCondition() throws IOException {
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";

        // 处理参数
        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";

        // 封装成对象
        Brand brand = new Brand();
        brand.setBrandName(brandName);
        brand.setStatus(status);
        brand.setCompanyName(companyName);

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // List<Brand> brands = brandMapper.selectByCondition(id, companyName, brandName);
        List<Brand> brands = brandMapper.selectByCondition(brand);
        System.out.println(brands);

        sqlSession.close();
    }

    @Test
    public void testAdd() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        int status = 1;
        String companyName = "遥遥领先";
        String brandName = "遥遥领先";
        String description = "喜欢拉踩同行营销的公司";
        int ordered = 100;

        Brand brand = new Brand();
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);
        brand.setOrdered(ordered);
        brand.setDescription(description);
        brand.setStatus(status);

        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // List<Brand> brands = brandMapper.selectAll(); 
        // for (Brand b : brands) {
        //     System.out.println(b.toString());
        // }
        System.out.println(brandMapper.selectAll().size());
        brandMapper.add(brand);
        sqlSession.commit();
        // System.out.println("=====================================================================");

        List<Brand> brands = brandMapper.selectAll(); 
        for (Brand b : brands) {
            System.out.println(b.toString());
        }
        System.out.println(brandMapper.selectAll().size());

        // 需要提交事务
    }

    @Test
    public void testUpdate() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        int status = 1;
        String companyName = "遥遥领先";
        String brandName = "遥遥领先";
        String description = "喜欢拉踩同行营销的公司，喜欢嘲讽某米的品牌";
        int ordered = 200;
        int id = 13;
        status = 0;

        Brand brand = new Brand();
        // brand.setBrandName(brandName);
        // brand.setCompanyName(companyName);
        // brand.setOrdered(ordered);
        // brand.setDescription(description);
        brand.setStatus(status);
        brand.setId(id);

        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);


        int count = brandMapper.update(brand);
        System.out.println(count);
        List<Brand> brands = brandMapper.selectAll();
        for (Brand b : brands) {
            System.out.println(b.toString());
        }
    }

    @Test
    public void testdeleteById() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        int id = 15;

        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);


        brandMapper.deleteById(id);
        List<Brand> brands = brandMapper.selectAll();
        for (Brand b : brands) {
            System.out.println(b.toString());
        }
    }

    @Test
    public void testdeleteByIds() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        int[] ids = {4, 5, 6};

        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);


        brandMapper.deleteByIds(ids);
        List<Brand> brands = brandMapper.selectAll();
        for (Brand b : brands) {
            System.out.println(b.toString());
        }
    }
}
