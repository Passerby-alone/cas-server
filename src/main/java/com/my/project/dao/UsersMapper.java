package com.my.project.dao;

import com.ezhiyang.cloud.commons.mybatis.mapper.BaseCurdMapper;
import com.my.project.entity.UsersDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper extends BaseCurdMapper<UsersDO, Long> {
}