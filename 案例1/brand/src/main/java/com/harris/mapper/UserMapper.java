package com.harris.mapper;

import java.util.List;

import com.harris.pojo.User;

public interface UserMapper {
    public List<User> selectAll();
    public User selectById(int id);
}
