package com.ems.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.api.domain.po.User;
import com.ems.system.mapper.UserMapper;
import com.ems.system.service.IUserService;
import com.ems.system.util.JwtHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    public boolean insert(User user) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("username", user.getUsername());
        User one = getOne(qw);
        if (one != null) return false;
        //    默认密码为 123456
        String password = DigestUtils.md5DigestAsHex("123456".getBytes());
        user.setPassword(password);
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        return save(user);
    }

    public boolean del(Serializable id, String cookie) {
        String pattern = "%22accessToken%22:%22(.+)%22";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(cookie);
        if (matcher.find()) {
            String token = matcher.group(1);
            //  验证token是否过期             验证权限为管理员
            if (!JwtHelper.verify(token)
                    && (Integer) JwtHelper.decode(token, "payload", "map").get("role") == 0) {
                return removeById(id);
            }
        }
        return false;
    }

    public boolean updateUser(User user, String cookie) {
        String pattern = "%22accessToken%22:%22(.+)%22";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(cookie);
        if (matcher.find()) {
            String token = matcher.group(1);
            if (!JwtHelper.verify(token)) {
                user.setUpdateTime(new Date());
                return updateById(user);
            }
        }
        return false;
    }
}

