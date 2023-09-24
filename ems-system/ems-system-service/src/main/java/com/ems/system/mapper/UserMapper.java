package com.ems.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ems.system.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
