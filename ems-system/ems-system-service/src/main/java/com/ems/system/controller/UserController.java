package com.ems.system.controller;

import com.ems.api.domain.po.User;
import com.ems.system.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final IUserService IUserService;

    //新增用户
    @PostMapping("/insert")
    public boolean insert(@RequestBody User user) {
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(password);
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        return IUserService.save(user);
    }

    //删除用户   状态变为停用
    @DeleteMapping("/delete/{id}")
    public boolean del(@PathVariable Serializable id, @RequestHeader("Cookie") String cookie) {
        return IUserService.del(id, cookie);
    }

    //修改密码
    @PutMapping("/update/passwd")
    public boolean updatePassWd(@RequestBody User user, @RequestHeader("Cookie") String cookie) {
        return IUserService.updatePassWd(user, cookie);
    }
}
