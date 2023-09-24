package com.ems.system.controller;

import com.ems.system.entity.User;
import com.ems.system.service.UserService;
import com.ems.system.util.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //新增用户
    @PostMapping("/insert")
    public boolean insert(@RequestBody User user) {
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(password);
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        return userService.save(user);
    }

    //删除用户   状态变为停用
    @DeleteMapping("/delete/{id}")
    public boolean del(@PathVariable Serializable id, @RequestHeader("token") String token) {
        Integer role = (Integer) JwtHelper.decode(token, "payload", "map").get("role");
        //  验证token是否过期             验证权限为管理员
        if (!JwtHelper.verify(token) && role == 0) {
            User user = userService.getById(id);
            user.setIsActive(0);
            return userService.updateById(user);
        }
        return false;
    }

    //修改密码
    @PutMapping("/update/passwd")
    public boolean update(@RequestBody User user, @RequestHeader("token") String token) {
        if (!JwtHelper.verify(token)) {
            return userService.updateById(user);
        }
        return false;
    }
}
