package com.tiktok.task.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface AdminMapper {
    HashMap<String,Object> HomePage(Long userId);
}
