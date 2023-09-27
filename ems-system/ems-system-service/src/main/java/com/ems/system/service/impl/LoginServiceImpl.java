package com.ems.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.api.domain.po.User;
import com.ems.system.mapper.UserMapper;
import com.ems.system.service.ILoginService;
import common.result.TokenData;
import common.util.JwtHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.*;

@Service
public class LoginServiceImpl extends ServiceImpl<UserMapper, User> implements ILoginService {

    public TokenData login(User user) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("username", user.getUsername());
        qw.eq("is_active", 1);
        User u = getOne(qw);
        if (u == null) return null;
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        if (u.getPassword().equals(password)) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", u.getId());
            map.put("username", u.getUsername());
            map.put("role", u.getRole());
            TokenData tokenData = JwtHelper.createToken("payload", map);
            tokenData.setRoles(new ArrayList<>(List.of(u.getRole().toString())));
            tokenData.setUsername(u.getUsername());
            return tokenData;
        }
        return null;
    }

    public User getUserInfoByToken(String token) {
        User user = new User();
        Map<String, Object> map = JwtHelper.decode(token, "payload").asMap();
        Long id = (Long) map.get("id");
        user.setId(id);
        String name = (String) map.get("name");
        user.setUsername(name);
        Integer role = (Integer) map.get("role");
        user.setRole(role);
        return user;
    }

    @Override
    public TokenData refresh(String refresh) {
        if (refresh == null) return null;
        if (JwtHelper.verify(refresh)) return null;
        Map<String, Object> map = JwtHelper.decode(refresh, "payload").asMap();
        return JwtHelper.createToken("payload", map);
    }
}

