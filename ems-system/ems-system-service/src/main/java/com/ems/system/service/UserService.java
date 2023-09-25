package com.ems.system.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.system.entity.User;
import com.ems.system.mapper.UserMapper;
import com.ems.system.util.JwtHelper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    public boolean del(Serializable id, String cookie) {
        String pattern = "%22accessToken%22:%22(.+)%22";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(cookie);
        if (matcher.find()) {
            String token = matcher.group(1);
            //  验证token是否过期             验证权限为管理员
            if (!JwtHelper.verify(token)
                    && (Integer) JwtHelper.decode(token, "payload", "map").get("role") == 0) {
                User user = getById(id);
                user.setIsActive(0);
                return updateById(user);
            }
        }
        return false;
    }

    public boolean updatePassWd(User user, String cookie) {
        String pattern = "%22accessToken%22:%22(.+)%22";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(cookie);
        if (matcher.find()) {
            String token = matcher.group(1);
            if (!JwtHelper.verify(token)) {
                return updateById(user);
            }
        }
        return false;
    }
}
