package com.ems.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ems.api.domain.po.User;
import com.ems.system.service.impl.UserServiceImpl;
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
    private UserServiceImpl userService;

    // 新增用户
    @PostMapping("/insert")
    public boolean insert(@RequestBody User user) {
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(password);
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        return userService.save(user);
    }

    // 删除用户   状态变为停用
    @DeleteMapping("/delete/{id}")
    public boolean del(@PathVariable Serializable id, @RequestHeader("Cookie") String cookie) {
        return userService.del(id, cookie);
    }

    // 修改密码
    @PutMapping("/update/passwd")
    public boolean updatePassWd(@RequestBody User user, @RequestHeader("Cookie") String cookie) {
        return userService.updatePassWd(user, cookie);
    }

    // 分页查询
    @GetMapping("/find")
    public IPage<User> find(Integer pageNum, Integer pageSize) {
        IPage<User> ip = new Page<>(pageNum, pageSize);
        return userService.page(ip);
    }
}
