package com.ems.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.system.entity.User;
import com.ems.system.mapper.UserMapper;
import com.ems.system.util.JwtHelper;
import common.result.TokenData;
import common.result.UserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService extends ServiceImpl<UserMapper, User> {
    @Autowired
    private UserMapper userMapper;

    public TokenData login(User user) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("username", user.getUsername());
        qw.eq("is_active", 1);
        User u = userMapper.selectOne(qw);
        if (u == null) return null;
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        if (u.getPassword().equals(password)) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", u.getId());
            map.put("name", u.getName());
            map.put("role", u.getRole());
            TokenData tokenData = JwtHelper.createToken("payload", map);
            tokenData.setRoles(u.getRole());
            return tokenData;
        }
        return null;
    }

    public User getUserInfoByToken(String token) {
        User user = new User();
        Long id = (Long) JwtHelper.decode(token, "payload", "map").get("id");
        user.setId(id);
        String name = (String) JwtHelper.decode(token, "payload", "map").get("name");
        user.setName(name);
        Integer role = (Integer) JwtHelper.decode(token, "payload", "map").get("role");
        user.setRole(role);
        return user;
    }
}
