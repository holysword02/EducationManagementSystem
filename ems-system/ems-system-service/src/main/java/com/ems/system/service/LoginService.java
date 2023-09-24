package com.ems.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.system.entity.User;
import com.ems.system.mapper.UserMapper;
import com.ems.system.util.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService extends ServiceImpl<UserMapper, User> {
    @Autowired
    private UserMapper userMapper;

    public String login(User user) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("username", user.getUsername());
        User u = userMapper.selectOne(qw);
        if (u == null) return null;
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        if (u.getPassword().equals(password)) {
            Map<String, Object> map = new HashMap<>();
            map.put("userId", u.getId());
            map.put("username", u.getUsername());
            map.put("role", u.getRole());
            return JwtHelper.createToken("payload", map);
        }
        return null;
    }

    public User getUserInfoByToken(String token) {
        User user = new User();
        Integer userId = (Integer) JwtHelper.decode(token,"payload","map").get("userId");
        user.setId(Long.valueOf(userId));
        String username = (String) JwtHelper.decode(token, "payload", "map").get("username");
        user.setUsername(username);
        Integer role = (Integer) JwtHelper.decode(token,"payload","map").get("role");
        user.setRole(role);
        return user;
    }
}
