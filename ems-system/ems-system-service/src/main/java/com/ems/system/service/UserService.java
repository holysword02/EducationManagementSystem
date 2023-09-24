package com.ems.system.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.system.entity.User;
import com.ems.system.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
}
