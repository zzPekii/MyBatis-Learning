package com.harris.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.harris.pojo.Brand;

public interface BrandMapper {
    // query all
    public List<Brand> selectAll();

    // query by id, return brand instance
    public Brand selectById(int id);

    // select by condition
    // 条件查询的三种参数接受方式
    // 1. 如果方法中有多个参数，需要用@Param("SQL参数占位符名称")
    // public List<Brand> selectByCondition(@Param("status") int status, 
    //                                      @Param("companyName") String companyName, 
    //                                      @Param("brandName") String brandName);
    public List<Brand> selectByCondition(Brand brand);
    // public List<Brand> selectByCondition(Map map);
}