package com.tiktok.task.mapper;

import com.tiktok.task.domain.ov.JuniorUserOV;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface AdminMapper {
    HashMap<String,Object> HomePage(Long userId);


    List<JuniorUserOV> ViewSubordinates(@Param("inviterId") Long inviterId,@Param("createBy") Long createBy);
}
