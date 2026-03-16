package com.harris.mapper;

import java.util.List;

import com.harris.pojo.User;

public interface UserMapper {
    List<User> selectAll();
    User selectById(int id);
} 